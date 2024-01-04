package de.lolmerkat


import de.lolmerkat.authorization.LinkConfig

//fun main(): Unit = runBlocking {
//    initServer()
//    scheduleShutdown(timeInSeconds = 30)
//    start(wait = true)
//}

//fun main() {
//    val state = AuthState.generate()
//
//    val link = AuthorizationLink(
//        codeChallenge = "47DEQpj8HBSa-_TImW-5JCeuQeRkm5NMpJWZG3hSuFU",
//        state = state.value
//    ).toUrl()
//
//    println(link)
//
//    initServer()
//    start(wait = true)
//}

fun main() {
    println(LinkConfig.fileContent)
}