package coroutines.examples

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Try to predict the order of A, B, C, D lines printing.
 */
fun main() = runBlocking {
    launch {
        delay(500L)
        println("#A. Run in scope of runBlocking")
    }

    coroutineScope {
        launch {
            delay(1000L)
            println("#B. Run in launch of coroutine scope")
        }

        delay(300L)
        println("#C. Run in coroutine scope")
    }

    println("#D. Under coroutine scope")
}