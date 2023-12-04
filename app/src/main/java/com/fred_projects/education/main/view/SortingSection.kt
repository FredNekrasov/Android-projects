package com.fred_projects.education.main.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fred_projects.R
import com.fred_projects.education.main.use_case.SortType
import com.fred_projects.education.main.use_case.SortingPW
import com.fred_projects.ui.FredRadioButton

@Composable
fun SortingSection(sortingPW: SortingPW = SortingPW.Date(SortType.Descending), onSortingChange: (SortingPW) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        LazyRow(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            item { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                FredRadioButton(inf = stringResource(R.string.PW), selected = sortingPW is SortingPW.PW, onSelect = { onSortingChange(SortingPW.PW(sortingPW.sortType)) })
                Spacer(Modifier.width(8.dp))
                FredRadioButton(inf = stringResource(R.string.Student), selected = sortingPW is SortingPW.Student, onSelect = { onSortingChange(SortingPW.Student(sortingPW.sortType)) })
                Spacer(Modifier.width(8.dp))
                FredRadioButton(inf = stringResource(R.string.Variant), selected = sortingPW is SortingPW.Variant, onSelect = { onSortingChange(SortingPW.Variant(sortingPW.sortType)) }) } }
            item { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                FredRadioButton(inf = stringResource(R.string.LVL), selected = sortingPW is SortingPW.LVL, onSelect = { onSortingChange(SortingPW.LVL(sortingPW.sortType)) })
                Spacer(Modifier.width(8.dp))
                FredRadioButton(inf = stringResource(R.string.Date), selected = sortingPW is SortingPW.Date, onSelect = { onSortingChange(SortingPW.Date(sortingPW.sortType)) })
                Spacer(Modifier.width(8.dp))
                FredRadioButton(inf = stringResource(R.string.Mark), selected = sortingPW is SortingPW.Mark, onSelect = { onSortingChange(SortingPW.Mark(sortingPW.sortType)) })
                Spacer(Modifier.width(8.dp)) } }
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            FredRadioButton(inf = stringResource(R.string.ascending_sort), selected = sortingPW.sortType is SortType.Ascending, onSelect = { onSortingChange(sortingPW.copy(SortType.Ascending)) })
            Spacer(Modifier.width(8.dp))
            FredRadioButton(inf = stringResource(R.string.descending_sort), selected = sortingPW.sortType is SortType.Descending, onSelect = { onSortingChange(sortingPW.copy(SortType.Descending)) })
        }
    }
}