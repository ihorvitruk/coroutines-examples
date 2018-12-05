package coroutines.examples

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() = runBlocking {
    val job = GlobalScope.launch {
        throw IndexOutOfBoundsException()
    }
    //try {
        job.join()
    //} catch (e: Exception) {

    //}
    val deferred = GlobalScope.async {
        throw ArithmeticException()
    }
   // try {
        deferred.await()
    //} catch (e: Exception) {

    //}
    println()
}