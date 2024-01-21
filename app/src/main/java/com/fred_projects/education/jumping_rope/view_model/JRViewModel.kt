package com.fred_projects.education.jumping_rope.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import com.fred_projects.education.jumping_rope.use_case.JRUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class JRViewModel @Inject constructor(private val useCases: JRUseCases) : ViewModel() {
    private var listItemsSF = MutableStateFlow<List<JRReps>>(emptyList())
    val listItemsS = listItemsSF.asStateFlow()
    private var itemSF = MutableStateFlow<JRReps?>(null)
    val itemS = itemSF.asStateFlow()
    private var int = 0
    private var getDataJob: Job? = null
    init {
        getJRData()
    }
    fun getJRData() {
        getDataJob?.cancel()
        getDataJob = useCases.getJRData.getData().onEach {
            listItemsSF.emit(it)
        }.launchIn(viewModelScope)
    }
    fun addEditRecord(id: Int? = null, count: Int?, date: String = LocalDate.now().toString()): Int {
        int = 0
        viewModelScope.launch {
            int = useCases.addJRRecord.addRecord(id, count, date)
        }
        return int
    }
    fun deleteRecord(record: JRReps){
        viewModelScope.launch {
            useCases.deleteJRRecord.deleteRecord(record)
        }
    }
    fun getRecord(id: Int?) {
        if (id == null) return
        viewModelScope.launch {
            itemSF.value = useCases.getJRRecord.getRecord(id)
        }
    }
}