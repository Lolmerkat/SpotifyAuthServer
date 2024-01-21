package de.lolmerkat

import de.lolmerkat.authorization.AuthState
import de.lolmerkat.authorization.AuthorizationLink


//fun main(): Unit = runBlocking {
//    initServer()
//    scheduleShutdown(timeInSeconds = 30)
//    start(wait = true)
//}

fun main() {
    val state = AuthState.generate()

    val link = AuthorizationLink.url
    println(link)

    initServer()
    start(wait = true)
}

//fun main() {
//    println(LinkConfig.fileContent)
//}