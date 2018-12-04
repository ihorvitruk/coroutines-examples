@file:Suppress("EXPERIMENTAL_API_USAGE")

package coroutines.examples

import kotlin.system.measureTimeMillis

private const val taskCount = 1_000_000

fun main() {
    println("Time: %s ms".format(measureTimeMillis {
        val threads: MutableList<Thread> = arrayListOf()
        repeat(taskCount) {
            val thread = Thread(Runnable {
                //Thread.sleep(1000) -- OutOfMemory for count ~> 10_000 if uncomment it !
                doSomeWork(it)
            })
            threads.add(thread)
            thread.start()
        }
        threads.forEach { it.join() }
    }))
}

private fun doSomeWork(@Suppress("UNUSED_PARAMETER") i: Int) {
    //do some work
}