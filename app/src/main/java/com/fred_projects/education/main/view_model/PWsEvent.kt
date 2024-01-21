package com.fred_projects.education.main.view_model

import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.education.main.use_case.sorting.SortingPW

sealed class PWsEvent {
    data class Sort(val sortingPW: SortingPW) : PWsEvent()
    data class DeletePW(val pw: PracticalWork) : PWsEvent()
    data object RestorePW : PWsEvent()
    data object ToggleSortSection : PWsEvent()
}