@file:Suppress("EXPERIMENTAL_API_USAGE")

package coroutines.examples

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private const val taskCount = 1_000_000

fun main() = runBlocking {
    println("Time: %s ms".format(measureTimeMillis {
        val jobs: MutableList<Job> = arrayListOf()
        repeat(taskCount) {
            jobs.add(launch {
                //delay(1000) - This DOES NOT have any influence on whole Task execution time or resources providing
                doSomeWork(it)
            })
        }
        jobs.forEach { it.join() }
    }))
}

private fun doSomeWork(@Suppress("UNUSED_PARAMETER") i: Int) {
}