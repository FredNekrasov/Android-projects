package com.fred_projects.education.rest_api.math

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.math.model.service.MineMath
import com.fred_projects.education.rest_api.math.use_case.GetSolutionFMS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MathVM @Inject constructor(private val get: GetSolutionFMS) : ViewModel() {
    private val resultMSF = MutableStateFlow<Pair<Resource, MineMath?>>(Resource.NONE to null)
    val resultSF = resultMSF.asStateFlow()
    private var searchJob: Job? = null

    fun onSearch(expression: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            get(expression).collectLatest {
                resultMSF.emit(it)
            }
        }
    }
}