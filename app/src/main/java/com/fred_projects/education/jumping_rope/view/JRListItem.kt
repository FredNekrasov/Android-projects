package com.fred_projects.education.jumping_rope.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fred_projects.R
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import com.fred_projects.ui.*

@Composable
fun JRListItem(record: JRReps, modifier: Modifier, onDeleteClick: () -> Unit) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.error, MaterialTheme.colors.onError)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.Date)}: ${record.date}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onError, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.count)}: ${record.count}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onError, overflow = TextOverflow.Ellipsis)
        }
        FredIconButton(click = onDeleteClick, Modifier.align(Alignment.BottomEnd), image = Icons.Default.Delete, description = stringResource(R.string.delete))
    }
}