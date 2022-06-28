package com.merdeev.kotlin.coroutines

import kotlinx.coroutines.*

fun doCoroutines() {
    println()
    println("Coroutines:")

    println("before runBlocking")
    runBlocking {
        println("start runBlocking")

        val job = launch("launch1", 600)
        launch("launch2", 500)
        coroutineScope(
            "coroutineScope1",
            400,
            { launch("launch3", 300) },
            { coroutineScope("coroutineScope2", 200) },
            { coroutineScope("coroutineScope3", 100) },
            { launch("launch3", 200) },
        )
        launch("launch4", 300)

        coroutineScope("coroutineScope4", 100)

        println("cancel launch1")
        job.cancelAndJoin()
        println("end runBlocking")
    }
    println("after runBlocking")
}

private fun CoroutineScope.launch(name: String, timeout: Long): Job {
    println("before $name")
    return launch {
        println("start $name")
        delay(timeout)
        println("end $name")
    }
}

private suspend fun coroutineScope(name: String, timeout: Long, vararg subs: suspend () -> Unit) {
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