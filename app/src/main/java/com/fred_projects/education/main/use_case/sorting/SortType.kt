package com.fred_projects.education.main.use_case.sorting

sealed class SortType {
    data object Ascending : SortType()
    data object Descending : SortType()
}