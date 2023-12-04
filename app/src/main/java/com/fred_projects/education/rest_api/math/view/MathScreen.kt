package com.fred_projects.education.rest_api.math.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fred_projects.R
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.math.MathVM
import com.fred_projects.ui.FredButton
import com.fred_projects.ui.FredTextField

@Composable
fun MathScreen(navController: NavController, viewModel: MathVM = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    var expression by rememberSaveable { mutableStateOf("") }
    val result = viewModel.resultSF.collectAsState().value.second
    val status = viewModel.resultSF.collectAsState().value.first

    Scaffold(scaffoldState = scaffoldState) { pv ->
        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            FredTextField(expression, { expression = it }, R.string.enter_expression)
            Spacer(Modifier.height(8.dp))
            FredButton({ viewModel.onSearch(expression) }, stringResource(R.string.result))
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                item {
                    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                        Text(
                            when(status) {
                                Resource.LOADING -> stringResource(R.string.wait)
                                Resource.SUCCESS -> stringResource(R.string.result_is)
                                Resource.ERROR -> stringResource(R.string.error)
                                Resource.NONE -> ""
                                Resource.NO_INTERNET -> stringResource(R.string.no_internet)
                                Resource.SOMETHING_WRONG -> stringResource(R.string.something_wrong)
                            }
                        )
                        if (result != null) {
                            MathInfoItem(
                                modifier = Modifier.fillMaxSize().padding(pv).padding(16.dp),
                                expression = result.expression,
                                result = result.result
                            )
                        }
                        FredButton({ navController.navigateUp() }, stringResource(R.string.go_back))
                    }
                }
            }
        }
    }
}