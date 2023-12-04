package com.fred_projects.education.jumping_rope.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fred_projects.education.jumping_rope.JRViewModel
import com.fred_projects.R
import com.fred_projects.ui.FredButton
import com.fred_projects.ui.FredIconButton
import com.fred_projects.ui.FredTextField
import kotlinx.coroutines.launch

@Composable
fun JRList(viewModel: JRViewModel, context: ComponentActivity) {
    val scope = rememberCoroutineScope()
    val list = viewModel.listItemsS.collectAsState()
    val jRRecord = viewModel.itemS.collectAsState()
    val scaffoldState = rememberScaffoldState()
    var countOfJumps by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var isShowDialog by rememberSaveable { mutableStateOf(false) }
    var inf: Int
    val recordDeleted = stringResource(R.string.record_deleted)
    val errors = stringResource(R.string.error)
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.CRUD), style = MaterialTheme.typography.h6) },
            actions = { FredIconButton(click = { viewModel.getJRData() }, image = Icons.Default.Refresh, description = stringResource(R.string.menu)) }
        )
    }, scaffoldState = scaffoldState) { padding ->
        Column(Modifier.padding(padding), Arrangement.Center, Alignment.CenterHorizontally) {
            Spacer(Modifier.height(16.dp))
            LazyColumn(Modifier.wrapContentSize()){
                items(list.value){ record ->
                    Spacer(Modifier.height(16.dp))
                    JRListItem(record,
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                viewModel.getRecord(record.id)
                                isShowDialog = true
                            },
                        onDeleteClick = {
                            viewModel.deleteRecord(record)
                            scope.launch { scaffoldState.snackbarHostState.showSnackbar(recordDeleted) }
                        }
                    )
                }
                item {
                    Row(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterVertically) { FredButton({ context.finish() }, stringResource(R.string.go_back)) }
                }
            }
        }
    }
    if (isShowDialog) {
        Dialog({ isShowDialog = false }) {
            Column(Modifier.wrapContentSize().background(MaterialTheme.colors.background), Arrangement.Center, Alignment.CenterHorizontally) {
                if (jRRecord.value != null) {
                    countOfJumps = jRRecord.value?.count!!.toString()
                    date = jRRecord.value?.date!!
                }
                Text(text = "${stringResource(R.string.add)} / ${stringResource(R.string.update)}", Modifier.padding(16.dp), fontSize = 20.sp)
                LazyColumn {
                    item {
                        Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                            FredTextField(countOfJumps, { if (((it.toIntOrNull() != null) && (it.toInt() > 0)) || (it == "")) countOfJumps = it }, R.string.enterPW)
                            Spacer(Modifier.height(8.dp))
                            FredTextField(date, { date = it }, R.string.enterDate)
                            Spacer(Modifier.height(8.dp))
                            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                                FredIconButton({ isShowDialog = false }, Modifier.padding(4.dp).border(2.dp, MaterialTheme.colors.onBackground, MaterialTheme.shapes.small), image = Icons.Default.ArrowBack, description = stringResource(R.string.go_back))
                                FredIconButton({
                                    inf = if (jRRecord.value != null) viewModel.addEditRecord(jRRecord.value?.id, countOfJumps.toIntOrNull(), date)
                                    else viewModel.addEditRecord(count = countOfJumps.toIntOrNull())
                                    if (inf != 0) scope.launch { scaffoldState.snackbarHostState.showSnackbar(errors) }
                                    isShowDialog = false
                                }, Modifier.padding(4.dp).border(2.dp, MaterialTheme.colors.onBackground, MaterialTheme.shapes.small), image = Icons.Default.Done, description = stringResource(R.string.update))
                            }
                        }
                    }
                }
            }
        }
    }
}