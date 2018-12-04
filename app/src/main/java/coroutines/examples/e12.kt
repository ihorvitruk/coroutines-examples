package coroutines.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() = runBlocking {
    val notifications = userNotifications()
    launch {
        notifications.consumeEach {
            println(it) // user receive notifications producer is not closed, or coroutineContext does not cancel all the childen!
        }
    }
    delay(5000)
    //coroutineContext.cancelChildren() //first variant how to stop
    notifications.cancel() //second variant how to stop
}

private fun CoroutineScope.userNotifications() = produce {
    while (true) {
        val delay = Math.random() * 1000L
        val userId = Math.random() * 100
        delay(delay.toLong())
        send("Notification from user #${userId.toLong()}")
    }
}