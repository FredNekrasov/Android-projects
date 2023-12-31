package com.fred_projects.education.rest_api.anime.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fred_projects.R
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes
import com.fred_projects.ui.FredCard

@Composable
fun AQListItem(aq: AnimeQuotes, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), Color.Magenta, Color.Black)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.anime)}: ${aq.anime}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.character)}: ${aq.character}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.quote)}: ${aq.quote}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
        }
    }
}