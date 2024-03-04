package com.fred_projects.education.rest_api.anime.view_model

import androidx.lifecycle.*
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.anime.model.repository.IAQRepository
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeQuotesVM @Inject constructor(private val repository: IAQRepository) : ViewModel() {
    private val resultMSF = MutableStateFlow<Pair<Resource, List<AnimeQuotes>?>>(Resource.NONE to null)
    val resultSF = resultMSF.asStateFlow()
    fun onSearch(anime: String) {
        viewModelScope.launch {
            if (anime.isEmpty() || anime.isBlank()) resultMSF.emit(Resource.ERROR to null)
            else {
                repository.getAQ(anime.lowercase()).collectLatest {
                    resultMSF.emit(it)
                }
            }
        }
    }
}