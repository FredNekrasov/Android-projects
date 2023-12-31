package com.fred_projects.education.rest_api.astronomy.view

import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fred_projects.MainActivity
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import com.fred_projects.ui.FredCard

@Composable
fun SIListItem(info: StarInfo, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize().border(width = 4.dp, color = Color.Black, shape = MaterialTheme.shapes.medium), Color.White, Color.Black)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text(info.name, Modifier.testTag(MainActivity.TEXT_NAME), style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(info.host.toString(), Modifier.testTag(MainActivity.TEXT_HOST), style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(info.references, Modifier.testTag(MainActivity.TEXT_REFERENCES), style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(8.dp))
            Text(info.ra, Modifier.testTag(MainActivity.TEXT_RA), style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
            Spacer(Modifier.height(8.dp))
            Text(info.dec, Modifier.testTag(MainActivity.TEXT_DEC), style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
            Spacer(Modifier.height(8.dp))
            Text(info.radius.toString(), Modifier.testTag(MainActivity.TEXT_RADIUS), style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
        }
    }
}