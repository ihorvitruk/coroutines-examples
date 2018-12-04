package coroutines.examples


/**
 * yield allows to generate streams (sequences, lists) on the fly
 *
 * yield provides an element of sequence, than stops generating with remembering if suspension point
 * of generating code, then handle current element, and then come back to suspension point to continue generating
 *
 * yield is a FUNCTION in kotlin
 *
 * yield is a SUSPEND function
 */
private fun sequence() = sequence {
    var i = 10

    println("Prepare first time")
    i *= 2
    yield(i)

    println("Prepare second time")
    i *= 5
    yield(i)

    println("Prepare third time")
    i *= 7
    yield(i)
}


fun main() {
    sequence().forEach {
        println("Some processing of $it")
    }
}
