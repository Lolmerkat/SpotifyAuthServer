package de.lolmerkat.extensions

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

private const val possibleCharacters: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-~"

fun generateRandomString(length: Int): String {
    var result = ""
    for (i in 0..length) {
        result += possibleCharacters.random()
    }
    return result
}

fun String.hashS256(): ByteArray {
    val digest = MessageDigest.getInstance("SHA-256")
    val hashedBytes = digest.digest(this.toByteArray(StandardCharsets.UTF_8))
    return hashedBytes
}