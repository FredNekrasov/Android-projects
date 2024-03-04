package com.fred_projects.education.rest_api.astronomy.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.primaryVariant, MaterialTheme.colors.onBackground)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text(info.name, Modifier.testTag(MainActivity.TEXT_NAME), style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onBackground, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(info.host.toString(), Modifier.testTag(MainActivity.TEXT_HOST), style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onBackground, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(info.references, Modifier.testTag(MainActivity.TEXT_REFERENCES), style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onBackground, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(8.dp))
            Text(info.ra, Modifier.testTag(MainActivity.TEXT_RA), color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
            Spacer(Modifier.height(8.dp))
            Text(info.dec, Modifier.testTag(MainActivity.TEXT_DEC), color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
            Spacer(Modifier.height(8.dp))
            Text(info.radius.toString(), Modifier.testTag(MainActivity.TEXT_RADIUS), color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.body2, fontWeight = FontWeight.Light)
        }
    }
}