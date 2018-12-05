package coroutines.examples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>(10)
    val sender = launch {
        repeat(20) {
            println("Suggest $it")
            channel.send(it) //will suspend when buffer is full
        }
    }
    delay(1000)
    sender.cancel()
}