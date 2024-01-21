package com.fred_projects.education.jumping_rope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.fred_projects.education.jumping_rope.view.JRList
import com.fred_projects.education.jumping_rope.view_model.JRViewModel
import com.fred_projects.ui.theme.FredProjectsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SensorAndAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: JRViewModel by viewModels()
        setContent {
            FredProjectsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    JRList(viewModel, this)
                }
            }
        }
    }
}