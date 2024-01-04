package de.lolmerkat.extensions

private const val possibleCharacters: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-~"

fun generateRandomString(length: Int): String {
    var result = ""
    for (i in 0..length) {
        result += possibleCharacters.random()
    }
    return result
}