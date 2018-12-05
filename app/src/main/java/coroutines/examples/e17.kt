package coroutines.examples

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Execution time: %s".format(measureTimeMillis {
        println(provideResults())
    }))
}

private suspend fun task1(): String? {
    return try {
        delay(2000)
        "result 1"

    } catch (e: CancellationException) {
        println("Task1 has been cancelled by a parent")
        return null
    }
}

@Throws(RuntimeException::class)
private suspend fun task2(): String {
    delay(1000)
    throw RuntimeException()
}

private suspend fun CoroutineScope.provideResults(): String {
    val job1 = async { task1() }
    val job2 = async { task2() }
    return "Results: ${job1.await()}, ${job2.await()}"
}