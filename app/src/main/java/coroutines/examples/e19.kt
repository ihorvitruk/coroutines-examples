package coroutines.examples

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                println("Now in $coroutineContext")
                withContext(ctx2) {
                    println("Now in $coroutineContext")
                }
                println("Now in $coroutineContext")
            }
        }
    }
}