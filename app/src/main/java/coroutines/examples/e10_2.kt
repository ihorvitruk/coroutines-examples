package coroutines.examples

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try{
            Thread.sleep(2000L) //emulate some i/o or computation
            yield()
            println("Message after emulated work") //let it be update UI "null" view after activity close.
        } catch (e: CancellationException) {
            println("Do something if task has been cancelled")
        } finally {
            println("Do some final work in all cases")
        }
    }
    delay(1000L)
    println("Cancel job")
    job.cancelAndJoin()
}