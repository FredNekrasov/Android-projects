package com.fred_projects.education.rest_api.astronomy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import com.fred_projects.education.rest_api.astronomy.use_case.GetStarInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarInfoVM @Inject constructor(private val getStarInfo: GetStarInfo) : ViewModel() {
    private val starInfoSF = MutableStateFlow<Pair<Resource, List<StarInfo>?>>((Resource.NONE to null))
    val starInfoS = starInfoSF.asStateFlow()
    private var searchJob: Job? = null
    fun onSearch(ra: String, dec: String, radius: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getStarInfo(ra, dec, radius.toFloatOrNull()).collectLatest {
                starInfoSF.emit(it)
            }
        }
    }
}