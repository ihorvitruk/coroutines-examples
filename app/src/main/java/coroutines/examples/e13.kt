@file:Suppress("EXPERIMENTAL_API_USAGE", "EXPERIMENTAL_OVERRIDE")

package coroutines.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

/**
 * Assume that there are 3 users in same messenger app. We are logged in as a User #1, and should see private
 * messages from User 2 and User 3 until leave a messenger
 */

fun main() = runBlocking {

    val messengerServer = MessengerServer(Channel(10))
    messengerServer.runServer(this)

    val user1 = User(1, true)
    val user2 = User(2, false)
    val user3 = User(3, false)

    user1.openMessenger(messengerServer)
    user2.openMessenger(messengerServer)
    user3.openMessenger(messengerServer)

    user1.sentMessage("message from user1 to user2", user2)
    user2.sentMessage("message from user2 to user3", user3)
    user2.sentMessage("message from user2 to user1", user1)
    delay(1000)
    user1.leaveMessenger()
    user3.sentMessage("message from user3 to user1 after user 1 left out", user1)
    user3.sentMessage("message from user3 to user2 after user 1 left out", user2)

    delay(30_000)
    messengerServer.shutdownServer()
}

class User(
    private val id: Int,
    private val currentUser: Boolean
) : CoroutineScope {

    private var currentChannel: SendChannel<Message>? = null
    private var job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    fun openMessenger(messengerServer: MessengerServer) {
        launch {
            currentChannel = messengerServer.channel
        }
    }

    fun leaveMessenger() {
        job.cancel()
        currentChannel = null
    }

    fun sentMessage(messageString: String, receiver: User) {
        val message = Message(this, receiver, messageString)
        launch {
            currentChannel?.send(message)
        }
    }

    fun receiveMessage(message: Message) {
        if (currentUser) {
            println("${message.sender} wrote: ${message.message}")
        }
    }

    override fun toString() = "User #$id"
}

class Message(val sender: User, val receiver: User, val message: String)

class MessengerServer(val channel: Channel<Message>) {
    fun shutdownServer() {
        channel.close()
    }

    fun runServer(scope: CoroutineScope) = scope.launch {
        channel.consumeEach {
            if (it.receiver.coroutineContext.isActive) {
                it.receiver.receiveMessage(it)
            }
        }
    }
}

