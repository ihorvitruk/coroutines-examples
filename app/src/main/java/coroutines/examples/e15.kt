package coroutines.examples

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Execution time: %s".format(measureTimeMillis {
        task1()
        task2()
    }))
}

private suspend fun task1() {
    delay(1000)
}

private suspend fun task2() {
    delay(2000)
}