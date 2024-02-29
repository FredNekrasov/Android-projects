package com.fred_projects.education.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fred_projects.R
import com.fred_projects.education.main.use_case.sorting.*
import com.fred_projects.ui.FredRadioButton

@Composable
fun SortingSection(sortingPW: SortingPW = SortingPW.Date(SortType.Descending), onSortingChange: (SortingPW) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        LazyRow(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    FredRadioButton(stringResource(R.string.PW), sortingPW is SortingPW.PW) { onSortingChange(SortingPW.PW(sortingPW.sortType)) }
                    Spacer(Modifier.width(8.dp))
                    FredRadioButton(stringResource(R.string.Student), sortingPW is SortingPW.Student) { onSortingChange(SortingPW.Student(sortingPW.sortType)) }
                    Spacer(Modifier.width(8.dp))
                    FredRadioButton(stringResource(R.string.Variant), sortingPW is SortingPW.Variant) { onSortingChange(SortingPW.Variant(sortingPW.sortType)) }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    FredRadioButton(stringResource(R.string.LVL), sortingPW is SortingPW.LVL) { onSortingChange(SortingPW.LVL(sortingPW.sortType)) }
                    Spacer(Modifier.width(8.dp))
                    FredRadioButton(stringResource(R.string.Date), sortingPW is SortingPW.Date) { onSortingChange(SortingPW.Date(sortingPW.sortType)) }
                    Spacer(Modifier.width(8.dp))
                    FredRadioButton(stringResource(R.string.Mark), sortingPW is SortingPW.Mark) { onSortingChange(SortingPW.Mark(sortingPW.sortType)) }
                    Spacer(Modifier.width(8.dp))
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            FredRadioButton(stringResource(R.string.ascending_sort), sortingPW.sortType is SortType.Ascending) { onSortingChange(sortingPW.copy(SortType.Ascending)) }
            Spacer(Modifier.width(8.dp))
            FredRadioButton(stringResource(R.string.descending_sort), sortingPW.sortType is SortType.Descending) { onSortingChange(sortingPW.copy(SortType.Descending)) }
        }
    }
}