package com.fred_projects.education.solving_the_inequality.mvvm

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VM: ViewModel() {
    private val model = Model()
    private val mutableStateView = MutableStateFlow("")
    val stateView = mutableStateView.asStateFlow()
    fun solution(n1: String, n2: String) {
        viewModelScope.launch {
            val result = model.checkData(n1.toFloatOrNull(), n2.toFloatOrNull())
            mutableStateView.emit(result.first.getSolution(result.second))
        }
    }
}