package com.fred_projects.education.main.view

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fred_projects.R
import com.fred_projects.education.ScreensRoute
import com.fred_projects.education.main.view_model.MainListVM
import com.fred_projects.education.main.view_model.PWsEvent
import com.fred_projects.ui.FredDrawerBody
import com.fred_projects.ui.FredDrawerHeader
import com.fred_projects.ui.FredFloatingActionButton
import com.fred_projects.ui.FredTextField
import com.fred_projects.ui.FredTopBar
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
            Icons.Default.List,
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
                exit = fadeOut() + slideOutVertically()) {
                SortingSection(
                    sortingPW = state.value.sortingPW,
                    onSortingChange = { viewModel.onEvent(PWsEvent.Sort(it)) }
                )
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