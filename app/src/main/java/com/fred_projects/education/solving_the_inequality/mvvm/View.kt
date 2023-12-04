package com.fred_projects.education.solving_the_inequality.mvvm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fred_projects.R
import com.fred_projects.education.solving_the_inequality.EnterNumber
import com.fred_projects.education.solving_the_inequality.NinthPW
import com.fred_projects.ui.FredButton
import kotlinx.coroutines.launch

@Composable
fun EleventhPW(nav: NavController, viewModel: VM) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        NinthPW(Modifier)
        Spacer(Modifier.height(8.dp))
        val error = stringResource(R.string.error)
        val inequality = stringResource(R.string.inequality)
        Text("${stringResource(R.string.first_number)}x + ${stringResource(R.string.second_number)} < 0", Modifier)
        var firstN by rememberSaveable { mutableStateOf("") }
        EnterNumber(firstN, { firstN = it }, Modifier, R.string.first_number)
        var secondN by rememberSaveable { mutableStateOf("") }
        EnterNumber(secondN, { secondN = it }, Modifier, R.string.second_number)
        var result by rememberSaveable { mutableStateOf("") }
        FredButton({
            viewModel.solution(firstN, secondN)
            viewModel.viewModelScope.launch {
                launch {
                    viewModel.stateView.collect {
                        result = when (it) {
                            "1" -> error
                            "2" -> inequality
                            else -> it
                        }
                    }
                }
            }
        }, stringResource(R.string.result))
        Text(result, Modifier)
        FredButton(click = { nav.navigateUp() }, inf = stringResource(R.string.go_back))
    }
}