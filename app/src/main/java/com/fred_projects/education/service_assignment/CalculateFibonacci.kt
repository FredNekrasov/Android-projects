package com.fred_projects.education.service_assignment

import android.os.Binder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.math.BigInteger

//Реализуйте программу вычисления 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи.
class CalculateFibonacci(number: Int): Binder() {
    private val scope by lazy { CoroutineScope(Dispatchers.Default) }
    private val scopeToCancel by lazy { CoroutineScope(Dispatchers.Default) }
    private val resultMSF = MutableStateFlow<Pair<BigInteger?, State>>(null to State.INITIALIZATION)
    val resultSF = resultMSF.asStateFlow()
    private val job = scope.launch {
        delay(3000)
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
        resultMSF.emit(result to State.NORMAL)
    }
    fun cancel(){
        scopeToCancel.launch {
            job.cancelAndJoin()
            resultMSF.emit(null to State.ERROR)
        }
    }
}