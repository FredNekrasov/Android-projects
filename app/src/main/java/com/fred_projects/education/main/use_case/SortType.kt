package com.fred_projects.education.main.use_case

sealed class SortType {
    object Ascending : SortType()
    object Descending : SortType()
}