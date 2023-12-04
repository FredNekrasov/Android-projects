package com.fred_projects.education

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import com.fred_projects.R
import com.fred_projects.education.jumping_rope.JRViewModel
import com.fred_projects.education.jumping_rope.SensorAndAnimation
import com.fred_projects.education.jumping_rope.view.StickMan
import com.fred_projects.ui.FredButton
import com.fred_projects.ui.theme.FredProjectsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@AndroidEntryPoint
class SecondLW : ComponentActivity() {
    private val manager by lazy { getSystemService(SENSOR_SERVICE) as SensorManager }
    private val sensor by lazy { manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val xFlow = MutableStateFlow(0f)
    val yFlow = MutableStateFlow(0f)
    val zFlow = MutableStateFlow(0f)
    val tFlow = MutableStateFlow(0L)
    private var accelerationCurrent = 0f
    private var accelerationLast = 0f
    private val listener = object: SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null){
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val stamp = event.timestamp
                lifecycleScope.launch {
                    xFlow.emit(x)
                    yFlow.emit(y)
                    zFlow.emit(z)
                    tFlow.emit(stamp)
                }
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: JRViewModel by viewModels()
        val manager1 by lazy { getSystemService(SENSOR_SERVICE) as SensorManager }
        val sensors = manager1.getSensorList(Sensor.TYPE_ALL)
        sensors.forEach {
            Log.d("fred", "name: ${it.name}, type: ${it.stringType}, vendor: ${it.vendor}, version: ${it.version}")
        }
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        setContent {
            FredProjectsTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val x by xFlow.collectAsState()
                    val y by yFlow.collectAsState()
                    val z by zFlow.collectAsState()
                    val t by tFlow.collectAsState()
                    var showJump by rememberSaveable { mutableStateOf(false) }
                    var res by rememberSaveable { mutableIntStateOf(0) }
                    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                        //BAI
                        val alpha = 0.8f
                        val gravity = sqrt((alpha * x * x + alpha * y * y + alpha * z * z))
                        accelerationLast = accelerationCurrent
                        accelerationCurrent = z - gravity
                        val diff = accelerationCurrent - accelerationLast
                        if (diff > 6) res++
                        Text("x: $x")
                        Text("y: $y")
                        Text("z: $z")
                        Text("timestamp: $t")
                        Text("${stringResource(R.string.count)}: $res")
                        FredButton({
                            viewModel.addEditRecord(count = res)
                            startActivity(Intent(this@SecondLW, SensorAndAnimation::class.java))
                        }, stringResource(R.string.save))
                        FredButton({ showJump = true }, stringResource(R.string.jump))
                        FredButton(click = { finish() }, inf = stringResource(R.string.go_back))
                    }
                    if (showJump){
                        Dialog(onDismissRequest = { showJump = false }) {
                            Column(Modifier.fillMaxSize().background(Color.White), Arrangement.Center, Alignment.CenterHorizontally) {
                                StickMan(res)
                                FredButton({ showJump = false }, stringResource(R.string.go_back))
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        manager.unregisterListener(listener)
    }
}