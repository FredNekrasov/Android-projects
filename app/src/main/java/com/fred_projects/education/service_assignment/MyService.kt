package com.fred_projects.education.service_assignment

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fred_projects.MainActivity
import com.fred_projects.R
import kotlinx.coroutines.*
import java.math.BigInteger

//В данной работе необходимо осуществить с использованием длинной арифметики достаточно долгое вычисление. Программа в ходе выполнения вычисления не должна «зависать».
//Должна быть возможность остановить вычисление по желанию пользователя. Подсказка: в работе разрешено использовать BigInteger (не возбраняется реализовать длинную арифметику «руками»).
//Вычисление должно осуществляться внутри Service в отдельном потоке, после вычисления результаты должны появиться в Activity, а если он неактивен, то должно появиться оповещение, кликнув по которому будет осуществлен переход на Activity с ответом.
//Реализуйте программу вычисления 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи.
class MyService : Service() {
    private var id = 1
    private val scope by lazy { CoroutineScope(Dispatchers.Default) }
    private val scopeToCancel by lazy { CoroutineScope(Dispatchers.Default) }
    private var job: Job? = null
    private fun calculateFibonacci(number: Int) {
        job = scope.launch {
            var result = BigInteger.ONE
            var a = 0L
            var b = 1L
            for (i in 0..(number-2)){
                yield()
                val fib = a + b
                a = b
                b = fib
            }
            for (i in 1..b){
                yield()
                result = result.multiply(BigInteger.valueOf(3L))
            }
            val innerIntent = Intent(this@MyService, TestService::class.java)
            innerIntent.action = MainActivity.SHOW_RESULT
            innerIntent.putExtra(RESULT, result.toString())
            val pendingIntent = PendingIntent.getActivity(this@MyService, 0, innerIntent, PendingIntent.FLAG_MUTABLE)
            val builder = NotificationCompat
                .Builder(this@MyService, MainActivity.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(getString(R.string.result))
                .setContentText("$result")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_launcher_background, getString(R.string.result), pendingIntent)
            val notification = builder.build()
            if (ActivityCompat.checkSelfPermission(this@MyService, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            }
            NotificationManagerCompat.from(this@MyService).notify(id, notification)
            id++
        }
    }
    override fun onBind(intent: Intent): IBinder {
        val number = intent.getIntExtra(NUMBER, 0)
        calculateFibonacci(number)
        return CalculateFibonacci(number)
    }
    override fun onDestroy() {
        super.onDestroy()
        scopeToCancel.launch {
            job?.cancelAndJoin()
        }
    }
    companion object{
        const val NUMBER = "number"
        const val RESULT = "qwerty"
    }
}