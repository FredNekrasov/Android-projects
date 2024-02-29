package com.fred_projects.education.rest_api.math.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fred_projects.ui.FredCard

@Composable
fun MathInfoItem(modifier: Modifier, expression: String, result: String) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize().border(2.dp, MaterialTheme.colors.primarySurface, MaterialTheme.shapes.small), MaterialTheme.colors.surface, MaterialTheme.colors.onSurface)
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text(expression, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(result, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface, overflow = TextOverflow.Ellipsis)
        }
    }
}