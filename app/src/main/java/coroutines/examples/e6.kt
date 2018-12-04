@file:Suppress("EXPERIMENTAL_API_USAGE")

package coroutines.examples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        delay(2000)
        println("Text after delay")
    }
    //job.join()
    println("Text under launch")
}
