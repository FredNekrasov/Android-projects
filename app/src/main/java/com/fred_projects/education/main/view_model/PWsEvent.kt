package com.fred_projects.education.main.view_model

import com.fred_projects.education.main.model.PracticalWork
import com.fred_projects.education.main.use_case.SortingPW

sealed class PWsEvent {
    data class Sort(val sortingPW: SortingPW) : PWsEvent()
    data class DeletePW(val pw: PracticalWork) : PWsEvent()
    object RestorePW : PWsEvent()
    object ToggleSortSection : PWsEvent()
}