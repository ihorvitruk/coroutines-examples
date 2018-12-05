package coroutines.examples

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Time: $time")
}

var counter: Int = 0

fun main() = runBlocking {
    GlobalScope.massiveRun {
        counter++
    }
    println("Counter = $counter")
}