package coroutines.examples

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("job1: Run from GlobalScope")
            delay(1000)
            println("job1: Run from GlobalScope after delay")
        }
        launch {
            delay(100)
            println("job2: Child of parent coroutine.")
            delay(1000)
            println("job2: Child after delay")
        }
    }
    delay(500)
    request.cancel()
    delay(1000)
    println("End")
}