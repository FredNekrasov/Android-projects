package com.fred_projects.education.rest_api.anime.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.fred_projects.R
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes

@Composable
fun AQListItem(aq: AnimeQuotes, modifier: Modifier, cornerRadius: Dp = 10.dp, cutCornerSize: Dp = 30.dp) {
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
                drawRoundRect(color = Color.White, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
                drawRoundRect(color = Color.Black, topLeft = Offset(size.width - cutCornerSize.toPx(), -100f), size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f), cornerRadius = CornerRadius(cornerRadius.toPx()))
            }
        }
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            Text("${stringResource(R.string.anime)}: ${aq.anime}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.character)}: ${aq.character}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${stringResource(R.string.quote)}: ${aq.quote}", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
        }
    }
}