package coroutines.examples

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        Thread.sleep(2000L) //emulate some i/o or computation
        yield()
        //if (isActive) {
            println("Message after emulated work") //let it be update UI "null" view after activity close.
        //}
    }
    delay(1000L)
    println("Cancel job")
    job.cancelAndJoin()
}