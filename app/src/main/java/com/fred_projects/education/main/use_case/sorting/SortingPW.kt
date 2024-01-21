package com.fred_projects.education.main.use_case.sorting

sealed class SortingPW(val sortType: SortType) {
    class PW(sortType: SortType) : SortingPW(sortType)
    class Student(sortType: SortType) : SortingPW(sortType)
    class Variant(sortType: SortType) : SortingPW(sortType)
    class LVL(sortType: SortType) : SortingPW(sortType)
    class Date(sortType: SortType) : SortingPW(sortType)
    class Mark(sortType: SortType) : SortingPW(sortType)
    fun copy(sortType: SortType): SortingPW {
        return when(this){
            is PW -> PW(sortType)
            is Student -> Student(sortType)
            is Variant -> Variant(sortType)
            is LVL -> LVL(sortType)
            is Date -> Date(sortType)
            is Mark -> Mark(sortType)
        }
    }
}