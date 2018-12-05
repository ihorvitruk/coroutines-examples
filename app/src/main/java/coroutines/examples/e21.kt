package coroutines.examples

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = async(CoroutineName("My coroutine")) {
        delay(4000)
        println(coroutineContext)
        println(coroutineContext[CoroutineName])
    }
    job.join()
}