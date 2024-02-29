package com.fred_projects.education.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.fred_projects.R
import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.ui.*


@Composable
fun ListItem(pw: PracticalWork, modifier: Modifier, onDeleteClick: () -> Unit) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.primary, MaterialTheme.colors.onPrimary)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.PW)}: ${pw.pwName}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Student)}: ${pw.student}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Variant)}: ${pw.variantNumber}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.LVL)}: ${pw.levelNumber}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Date)}: ${pw.date}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Mark)}: ${pw.mark}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onPrimary, overflow = TextOverflow.Ellipsis)
            Image(rememberAsyncImagePainter(pw.image.toUri()), stringResource(R.string.photo), Modifier.height(200.dp))
        }
        FredIconButton(click = onDeleteClick, Modifier.align(Alignment.BottomEnd), image = Icons.Default.Delete, description = stringResource(R.string.delete))
    }
}