package coroutines.examples

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Execution time: %s".format(measureTimeMillis {
        val job1 = async { task1() }
        val job2 = async { task2() }
        println("Results: ${job1.await()}, ${job2.await()}")
    }))
}

private suspend fun task1(): String {
    delay(1000)
    return "result 1"
}

private suspend fun task2(): String {
    delay(2000)
    return "result 2"
}