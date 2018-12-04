package coroutines.examples

import kotlinx.coroutines.*

fun main() = runBlocking {

    lateinit var job: Job
    /**
     * @throws TimeoutCancellationException
     */
    withTimeout(1000) {
    //withTimeoutOrNull(1000L) {
        job = launch(Dispatchers.Default) {
            Thread.sleep(2000L)
            println("Message after emulated work")
        }
    }
    job.join()
    println("End")
}