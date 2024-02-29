package com.fred_projects.education.jumping_rope.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import com.fred_projects.R
import com.fred_projects.education.jumping_rope.view_model.JRViewModel
import com.fred_projects.ui.*
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
        TopAppBar({ Text(stringResource(R.string.CRUD), style = MaterialTheme.typography.h6) }, actions = { FredIconButton({ viewModel.getJRData() }, image = Icons.Default.Refresh, description = stringResource(R.string.menu)) })
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
                                FredIconButton({ isShowDialog = false }, Modifier.padding(4.dp).border(2.dp, MaterialTheme.colors.onBackground, MaterialTheme.shapes.small), image = Icons.Default.ArrowBackIosNew, description = stringResource(R.string.go_back))
                                FredIconButton({
                                    inf = if (jRRecord.value != null) viewModel.addEditRecord(jRRecord.value?.id, countOfJumps.toIntOrNull(), date) else viewModel.addEditRecord(count = countOfJumps.toIntOrNull())
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