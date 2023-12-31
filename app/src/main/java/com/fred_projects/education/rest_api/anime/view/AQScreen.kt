package com.fred_projects.education.rest_api.anime.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fred_projects.R
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.anime.AnimeQuotesVM
import com.fred_projects.ui.FredButton
import com.fred_projects.ui.FredTextField

@Composable
fun AQScreen(navController: NavController, viewModel: AnimeQuotesVM = hiltViewModel()) {
    var searchData by rememberSaveable { mutableStateOf("") }
    val state = viewModel.resultSF.collectAsState().value.first
    val res = viewModel.resultSF.collectAsState().value.second
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        Text(stringResource(R.string.anime_quotes))
        Spacer(Modifier.height(8.dp))
        Text(stringResource(R.string.aq_info))
        Spacer(Modifier.height(16.dp))
        FredTextField(searchData, { searchData = it }, R.string.anime)
        Spacer(Modifier.height(8.dp))
        FredButton(click = { viewModel.onSearch(searchData) }, inf = stringResource(R.string.search))
        Spacer(Modifier.height(8.dp))
        Text(
            when(state) {
                Resource.LOADING -> stringResource(R.string.wait)
                Resource.SUCCESS -> stringResource(R.string.result_is)
                Resource.ERROR -> stringResource(R.string.error)
                Resource.NONE -> ""
                Resource.NO_INTERNET -> stringResource(R.string.no_internet)
                Resource.SOMETHING_WRONG -> stringResource(R.string.something_wrong)
            }
        )
        FredButton({ navController.navigateUp() }, stringResource(R.string.go_back))
        if (res != null) {
            LazyColumn(Modifier.fillMaxSize()){
                items(res){ aq ->
                    Spacer(Modifier.height(16.dp))
                    AQListItem(aq,Modifier.fillMaxWidth())
                }
            }
        }
    }
}