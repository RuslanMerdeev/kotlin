package com.merdeev.kotlin.coroutines

import kotlinx.coroutines.*

fun doCoroutines() {
    println()
    println("Coroutines:")

    println("before runBlocking")
    runBlocking {
        println("start runBlocking")

        launch("launch1", 600)

        launch("launch2", 500)

        coroutineScope(
            "coroutineScope1",
            400,
            { launch("launch3", 300) },
        )

        coroutineScope("coroutineScope2", 100)

        println("end runBlocking")
    }
    println("after runBlocking")
}

private fun CoroutineScope.launch(name: String, timeout: Long) {
    println("before $name")
    launch {
        println("start $name")
        delay(timeout)
        println("end $name")
    }
}

private suspend fun coroutineScope(name: String, timeout: Long, vararg subs: () -> Unit) {
    println("before $name")
    coroutineScope {
        println("start $name")

        for (sub in subs) {
            sub()
        }

        delay(timeout)
        println("end $name")
    }
}