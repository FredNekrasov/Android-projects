package com.fred_projects.education.main.use_case.crud

import com.fred_projects.education.main.model.IMainRepository
import com.fred_projects.education.main.model.PracticalWork
import com.fred_projects.education.main.use_case.SortType
import com.fred_projects.education.main.use_case.SortingPW
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPWData(private val repository: IMainRepository) {
    operator fun invoke(sortingPW: SortingPW = SortingPW.Date(SortType.Descending)): Flow<List<PracticalWork>> {
        return repository.getData().map { pw ->
            when(sortingPW.sortType){
                is SortType.Ascending -> {
                    when(sortingPW){
                        is SortingPW.PW -> pw.sortedBy { it.pwName.lowercase() }
                        is SortingPW.Student -> pw.sortedBy { it.student.lowercase() }
                        is SortingPW.Variant -> pw.sortedBy { it.variantNumber }
                        is SortingPW.LVL -> pw.sortedBy { it.levelNumber }
                        is SortingPW.Date -> pw.sortedBy { it.date }
                        is SortingPW.Mark -> pw.sortedBy { it.mark }
                    }
                }
                is SortType.Descending -> {
                    when(sortingPW){
                        is SortingPW.PW -> pw.sortedByDescending { it.pwName.lowercase() }
                        is SortingPW.Student -> pw.sortedByDescending { it.student.lowercase() }
                        is SortingPW.Variant -> pw.sortedByDescending { it.variantNumber }
                        is SortingPW.LVL -> pw.sortedByDescending { it.levelNumber }
                        is SortingPW.Date -> pw.sortedByDescending { it.date }
                        is SortingPW.Mark -> pw.sortedByDescending { it.mark }
                    }
                }
            }
        }
    }
}