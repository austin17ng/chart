package com.vnpay.testapplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

fun main() {
    jusATest()
}

// just a test
fun jusATest() {
    runBlocking {
        val job = Job()
        val dispatcher = Dispatchers.IO
        val context = job + dispatcher
        val scope = CoroutineScope(context)
        val res = scope.launch {
            delay(3000L)
            println("im sorry if ")
        }


        delay(2000L)
        res.cancel()
        delay(3000L)

    }
}

// extensive work example
fun cancellableIntensiveWorkExample() {
    runBlocking {
        withContext(Dispatchers.IO) {
            val time = measureTimeMillis {
                doIntensiveWork()
            }
            println("it took $time")
        }
    }
}

suspend fun doIntensiveWork(): Long = withContext(Dispatchers.IO) {
    delay(3000L)
    100
}

//time out example
fun timeOutExample() {
    runBlocking {
        val timeOut = 3000L
        val time = 2000L
        val result = withTimeoutOrNull(timeOut) {
            delay(time)
            "okkkkk"
        }
        println(result)
    }
}

//several requests example
fun severalRequestExample() {
    runBlocking {
        val result = (1..5).map {
            async {
                fetchApi(it * 1000L)
            }
        }.awaitAll()
        println("-------")
        result.map {
            println("Result = $it")
        }
    }
}

suspend fun fetchApi(time: Long = 3000L): String {
    delay(time)
    val msg = "Hello $time"
    println(msg)
    return msg
}