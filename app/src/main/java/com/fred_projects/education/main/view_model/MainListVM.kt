package com.fred_projects.education.main.view_model

import androidx.lifecycle.*
import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.education.main.use_case.MainUseCases
import com.fred_projects.education.main.use_case.sorting.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainListVM @Inject constructor(private val databaseUseCases: MainUseCases): ViewModel() {
    private val pwSF = MutableStateFlow(PWState())
    val pwState = pwSF.asStateFlow()
    private var recentlyDeletedPW: PracticalWork? = null
    private var getDataJob: Job? = null
    fun onEvent(event: PWsEvent) {
        when(event){
            is PWsEvent.Sort -> {
                if ((pwState.value.sortingPW::class == event.sortingPW::class) && (pwState.value.sortingPW.sortType == event.sortingPW.sortType)) return
                getSortedPWs(event.sortingPW)
            }
            is PWsEvent.DeletePW -> {
                viewModelScope.launch {
                    databaseUseCases.deleteData(event.pw)
                    recentlyDeletedPW = event.pw
                }
            }
            is PWsEvent.RestorePW -> {
                viewModelScope.launch {
                    databaseUseCases.addData.restorePW(recentlyDeletedPW ?: return@launch)
                    recentlyDeletedPW = null
                }
            }
            is PWsEvent.ToggleSortSection -> {
                pwSF.value = pwState.value.copy(isSortingSectionVisible = !pwState.value.isSortingSectionVisible)
            }
        }
    }
    fun searchData(search: String) {
        if (search == "") getSortedPWs(pwSF.value.sortingPW)
        else {
            val res = pwSF.value.pws.filter { it.pwName.startsWith(search) || it.student.startsWith(search) || it.variantNumber.toString().startsWith(search) || it.levelNumber.toString().startsWith(search) || it.date.startsWith(search) || it.mark.toString().startsWith(search) }
            pwSF.value = pwState.value.copy(pws = res, sortingPW = pwSF.value.sortingPW)
        }
    }
    private fun getSortedPWs(sortingPW: SortingPW) {
        getDataJob?.cancel()
        getDataJob = databaseUseCases.getData(sortingPW).onEach {
            pwSF.value = pwState.value.copy(pws = it, sortingPW = sortingPW)
        }.launchIn(viewModelScope)
    }
    init {
        getSortedPWs(SortingPW.Date(SortType.Descending))
    }
}