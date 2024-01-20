package de.lolmerkat

import de.lolmerkat.authorization.AuthState
import de.lolmerkat.authorization.AuthorizationLink
import de.lolmerkat.authorization.LinkConfig
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


//fun main(): Unit = runBlocking {
//    initServer()
//    scheduleShutdown(timeInSeconds = 30)
//    start(wait = true)
//}

fun main() {
    val state = AuthState.generate()

    val link = AuthorizationLink.url
    println(link)
    println(Json.encodeToString(LinkConfig.Data()))

    initServer()
    start(wait = true)
}

//fun main() {
//    println(LinkConfig.fileContent)
//}