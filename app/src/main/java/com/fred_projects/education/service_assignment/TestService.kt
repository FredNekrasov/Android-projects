package com.fred_projects.education.service_assignment

import android.app.*
import android.content.*
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fred_projects.MainActivity
import com.fred_projects.R
import com.fred_projects.ui.*
import com.fred_projects.ui.theme.FredProjectsTheme

class TestService : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channel = NotificationChannel(MainActivity.NOTIFICATION_CHANNEL_ID, getString(R.string.message_header), NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = getString(R.string.description)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        setContent {
            FredProjectsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var number by rememberSaveable { mutableStateOf("") }
                    var binder by rememberSaveable { mutableStateOf<List<CalculateFibonacci>>(emptyList()) }
                    var id by rememberSaveable { mutableIntStateOf(0) }
                    var res by rememberSaveable { mutableStateOf<String?>("") }
                    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                        if (intent.action == MainActivity.SHOW_RESULT){
                            res = intent.getStringExtra(MyService.RESULT)
                            LazyColumn(Modifier.fillMaxSize()) {
                                item {
                                    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                                        Text("$res")
                                        FredButton(click = { finish() }, inf = stringResource(R.string.go_back))
                                    }
                                }
                            }
                        } else {
                            FredTextField(number, { if (((it.toIntOrNull() != null) && (it.toInt() >= 0)) || (it == "")) number = it }, R.string.enter_number)
                            FredButton({
                                val intent = Intent(this@TestService, MyService::class.java)
                                intent.action = "$id"
                                id++
                                intent.putExtra(MyService.NUMBER, number.toIntOrNull()?:4)
                                bindService(intent, object : ServiceConnection {
                                    override fun onServiceConnected(p0: ComponentName?, bind: IBinder?) {
                                        val localBinder = bind as? CalculateFibonacci
                                        if (localBinder != null) binder += listOf(localBinder)
                                    }
                                    override fun onServiceDisconnected(p0: ComponentName?) { message(getString(R.string.service_error)) }
                                }, BIND_AUTO_CREATE) }, stringResource(R.string.result))
                            LazyColumn {
                                items(binder){
                                    val result = it.resultSF.collectAsState()
                                    val message = when(result.value.second){
                                        State.INITIALIZATION -> stringResource(R.string.calculations_begun)
                                        State.NORMAL -> result.value.first.toString()
                                        State.ERROR -> stringResource(R.string.calculations_canceled)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(message, Modifier.padding(start = 8.dp, end = 8.dp))
                                        if (result.value.second == State.INITIALIZATION) FredButton({ it.cancel() }, stringResource(R.string.cancel))
                                    }
                                }
                                item {
                                    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                                        FredButton({ finish() }, stringResource(R.string.go_back))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}