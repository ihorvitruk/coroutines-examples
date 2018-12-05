package coroutines.examples

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Activity : CoroutineScope {
    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun onCreate() {
        job = Job()
        loadDataAndUpdateUi()
    }

    fun onDestroy() {
        //job.cancel()
    }

    private fun loadDataAndUpdateUi() {
        launch {
            val data = loadData()
            updateUi(data)
        }
    }

    private suspend fun loadData(): String {
        delay(3000)
        return "some data"
    }

    private fun updateUi(data: String) {
        // view can be null, if activity is destroyed
        println("Update ui with $data")
    }
}

fun main() = runBlocking {
    val activity = Activity()
    activity.onCreate()
    delay(500L)
    activity.onDestroy() // cancels all coroutines
    delay(5000)
}