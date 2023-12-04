package com.fred_projects.education.jumping_rope.view

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fred_projects.R
import com.fred_projects.education.jumping_rope.model.JRReps
import com.fred_projects.ui.FredIconButton

@Composable
fun JRListItem(record: JRReps, modifier: Modifier, onDeleteClick: () -> Unit) {
    val cornerRadius = 10.dp
    val cutCornerSize = 30.dp
    Box(modifier) {
        Canvas(Modifier.matchParentSize()){
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(color = Color.Cyan, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
                drawRoundRect(color = Color.Black, topLeft = Offset(size.width - cutCornerSize.toPx(), -100f), size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f), cornerRadius = CornerRadius(cornerRadius.toPx()))
            }
        }
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.Date)}: ${record.date}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.count)}: ${record.count}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
        }
        FredIconButton(click = onDeleteClick, Modifier.align(Alignment.BottomEnd), image = Icons.Default.Delete, description = stringResource(R.string.delete))
    }
}