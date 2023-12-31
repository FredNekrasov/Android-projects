package com.fred_projects.education.rest_api.astronomy.view

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fred_projects.MainActivity
import com.fred_projects.R
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.StarInfoVM
import com.fred_projects.ui.FredButton
import com.fred_projects.ui.FredTextField

@Composable
fun StarInfoScreen(navController: NavController, viewModel: StarInfoVM = hiltViewModel()) {
    var ra by rememberSaveable { mutableStateOf("") }
    var dec by rememberSaveable { mutableStateOf("") }
    var radius by rememberSaveable { mutableStateOf("") }
    val state = viewModel.starInfoS.collectAsState().value.first
    val res = viewModel.starInfoS.collectAsState().value.second
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        FredTextField(ra, { ra = it }, R.string.enter_ra, Modifier.testTag(MainActivity.TEXT_FIELD_RA))
        FredTextField(dec, { dec = it }, R.string.enter_dec, Modifier.testTag(MainActivity.TEXT_FIELD_DEC))
        FredTextField(radius, { radius = it }, R.string.enter_radius, Modifier.testTag(MainActivity.TEXT_FIELD_RADIUS))
        FredButton({ viewModel.onSearch(ra, dec, radius) }, stringResource(R.string.get_info), Modifier.testTag(MainActivity.GET_INFO_BUTTON))
        Spacer(Modifier.height(8.dp))
        Text(
            when(state) {
                Resource.NO_INTERNET -> stringResource(R.string.no_internet)
                Resource.SOMETHING_WRONG -> stringResource(R.string.something_wrong)
                Resource.LOADING -> stringResource(R.string.wait)
                Resource.SUCCESS -> stringResource(R.string.result_is)
                Resource.ERROR -> stringResource(R.string.error)
                Resource.NONE -> ""
            }, Modifier.testTag(MainActivity.SHOW_RESULT)
        )
        FredButton({ navController.navigateUp() }, stringResource(R.string.go_back))
        if (res != null) {
            LazyColumn(Modifier.fillMaxSize()){
                items(res){ starInfo ->
                    Spacer(Modifier.height(16.dp))
                    SIListItem(starInfo, Modifier.fillMaxWidth())
                }
            }
        }
    }
}