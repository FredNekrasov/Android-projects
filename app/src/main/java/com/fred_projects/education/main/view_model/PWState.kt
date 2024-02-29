package com.fred_projects.education.main.view_model

import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.education.main.use_case.sorting.*

data class PWState(
    val pws: List<PracticalWork> = emptyList(),
    val sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    val isSortingSectionVisible: Boolean = false
)