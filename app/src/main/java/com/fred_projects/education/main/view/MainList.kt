package com.fred_projects.education.main.view

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fred_projects.R
import com.fred_projects.education.ScreensRoute
import com.fred_projects.education.main.view_model.*
import com.fred_projects.ui.*
import kotlinx.coroutines.launch


@Composable
fun MainList(navController: NavController, context: Context, viewModel: MainListVM = hiltViewModel()){
    val scope = rememberCoroutineScope()
    var searchData by rememberSaveable { mutableStateOf("") }
    val state = viewModel.pwState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val recordDeleted = stringResource(R.string.record_deleted)
    val restoreRecord = stringResource(R.string.cancel)
    Scaffold(
        topBar = { FredTopBar(
            { scope.launch { scaffoldState.drawerState.open() } },
            { viewModel.onEvent(PWsEvent.ToggleSortSection) },
            Icons.Default.FilterList,
            stringResource(R.string.sort)
        ) },
        floatingActionButton = { FredFloatingActionButton(
            { navController.navigate(ScreensRoute.AddEditPage.route) },
            Icons.Default.Add,
            stringResource(R.string.add)
        ) },
        scaffoldState = scaffoldState,
        drawerContent = {
            FredDrawerHeader()
            FredDrawerBody(context, navController)
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding), Arrangement.Center, Alignment.CenterHorizontally) {
            AnimatedVisibility(
                state.value.isSortingSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                SortingSection(state.value.sortingPW) { viewModel.onEvent(PWsEvent.Sort(it)) }
            }
            Spacer(Modifier.height(16.dp))
            FredTextField(searchData, {
                searchData = it
                viewModel.searchData(searchData)
            }, R.string.search)
            LazyColumn(Modifier.fillMaxSize()){
                items(state.value.pws){ pw ->
                    Spacer(Modifier.height(16.dp))
                    ListItem(pw,
                        Modifier.fillMaxWidth().clickable { navController.navigate(ScreensRoute.AddEditPage.route + "?id=${pw.id}") },
                        onDeleteClick = {
                            viewModel.onEvent(PWsEvent.DeletePW(pw))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(recordDeleted, restoreRecord)
                                if (result == SnackbarResult.ActionPerformed) viewModel.onEvent(PWsEvent.RestorePW)
                            }
                        }
                    )
                }
            }
        }
    }
}