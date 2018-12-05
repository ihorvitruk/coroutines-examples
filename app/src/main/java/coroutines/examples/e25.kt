package coroutines.examples

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private suspend fun CoroutineScope.runParallel(action: suspend () -> Unit) {
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

var counter: Int = 0 // Variant #0. The init problem

//@Volatile // Variant #1. Not help
//var counter: Int = 0


//var counter = AtomicInteger(0) //Variant #2 Help, but is hard to scale for complex state,
// that odes not have-ready-to-use impls


fun main() = runBlocking {
    /*val counterContext = newSingleThreadContext("counterContext")
    CoroutineScope(counterContext).runParallel {*/  //Variant #4
    GlobalScope.runParallel {
        counter++
    }
    println("Counter: $counter")
}


