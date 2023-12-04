package com.fred_projects.education.rest_api.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.anime.model.repository.IAQRepository
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeQuotesVM @Inject constructor(private val repository: IAQRepository) : ViewModel() {
    private val resultMSF = MutableStateFlow<Pair<Resource, List<AnimeQuotes>?>>(Resource.NONE to null)
    val resultSF = resultMSF.asStateFlow()
    private var searchJob: Job? = null

    fun onSearch(anime: String) {
        if (anime.isEmpty() || anime.isBlank()) {
            resultMSF.value = (Resource.ERROR to null)
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repository.getAQ(anime).collectLatest {
                resultMSF.emit(it)
            }
        }
    }
}