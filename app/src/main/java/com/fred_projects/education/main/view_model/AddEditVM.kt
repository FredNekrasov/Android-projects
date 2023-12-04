package com.fred_projects.education.main.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.*
import com.fred_projects.education.main.use_case.MainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AddEditVM @Inject constructor(private val pwUseCases: MainUseCases, savedStateHandle: SavedStateHandle) : ViewModel() {
    private var currentPWId: Int? = null
    private var checkErrors = 0
    private val pwNameSF = MutableStateFlow("")
    val pwNameS = pwNameSF.asStateFlow()
    private val pwStudentSF = MutableStateFlow("")
    val pwStudentS = pwStudentSF.asStateFlow()
    private val pwVariantSF = MutableStateFlow("")
    val pwVariantS = pwVariantSF.asStateFlow()
    private val pwLvlSF = MutableStateFlow("")
    val pwLvlS = pwLvlSF.asStateFlow()
    private val pwDateSF = MutableStateFlow("")
    val pwDateS = pwDateSF.asStateFlow()
    private val pwMarkSF = MutableStateFlow("")
    val pwMarkS = pwMarkSF.asStateFlow()
    private val pwImageSF = MutableStateFlow("")
    val pwImageS = pwImageSF.asStateFlow()
    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1){
                viewModelScope.launch {
                    pwUseCases.getPW(id)?.also {
                        currentPWId = it.id
                        pwNameSF.emit(it.pwName)
                        pwStudentSF.emit(it.student)
                        pwVariantSF.emit(it.variantNumber.toString())
                        pwLvlSF.emit(it.levelNumber.toString())
                        pwDateSF.emit(it.date)
                        pwMarkSF.emit(it.mark.toString())
                        pwImageSF.emit(it.image)
                    }
                }
            }
        }
    }
    fun addRecord(pwName: String, student: String, variant: String, lvl: String, date: String, mark: String, image: String): Int {
        checkErrors = 0
        viewModelScope.launch {
            checkErrors += pwUseCases.addData(
                currentPWId,
                pwName,
                student,
                variant.toIntOrNull(),
                lvl.toIntOrNull(),
                date,
                mark.toIntOrNull(),
                image
            )
        }
        return checkErrors
    }
}