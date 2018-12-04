package coroutines.examples

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        val delay = 2000L
        delay(delay)
        println("This message should appear after $delay milliseconds")
    }
    delay(1300L)
    println("Cancel job")
    //job.cancel() // cancels the job
    //job.join() // waits for job's completion
    job.cancelAndJoin()
    println("End")
}