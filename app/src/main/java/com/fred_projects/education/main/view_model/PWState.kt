package com.fred_projects.education.main.view_model

import com.fred_projects.education.main.model.PracticalWork
import com.fred_projects.education.main.use_case.SortType
import com.fred_projects.education.main.use_case.SortingPW

data class PWState(
    val pws: List<PracticalWork> = emptyList(),
    val sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    val isSortingSectionVisible: Boolean = false
)