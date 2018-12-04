package coroutines.examples

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        Thread.sleep(2000L) //emulate some i/o or computation
        println("Message after emulated work")
    }
    delay(1000L)
    println("Cancel job")
    job.cancelAndJoin() // cancels the job and waits for its completion
}