package coroutines.examples

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

private fun loadData() {
    val data = URL("https://google.com").readText()
    println(data)
}

fun main() = runBlocking {
    launch {
        loadData()
    }
    println("Start loading data")
}
