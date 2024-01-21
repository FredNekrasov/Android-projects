package com.fred_projects.education.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.fred_projects.R
import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.ui.FredCard
import com.fred_projects.ui.FredIconButton


@Composable
fun ListItem(pw: PracticalWork, modifier: Modifier, onDeleteClick: () -> Unit) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), Color.Cyan, Color.Black)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.PW)}: ${pw.pwName}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Student)}: ${pw.student}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Variant)}: ${pw.variantNumber}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.LVL)}: ${pw.levelNumber}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Date)}: ${pw.date}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.Mark)}: ${pw.mark}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Image(rememberAsyncImagePainter(pw.image.toUri()), stringResource(R.string.photo), Modifier.height(200.dp))
        }
        FredIconButton(click = onDeleteClick, Modifier.align(Alignment.BottomEnd), image = Icons.Default.Delete, description = stringResource(R.string.delete))
    }
}