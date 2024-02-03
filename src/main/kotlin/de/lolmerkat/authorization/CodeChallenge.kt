package de.lolmerkat.authorization

import de.lolmerkat.extensions.base64encode
import de.lolmerkat.extensions.generateRandomString
import de.lolmerkat.extensions.hashS256

object CodeChallenge {
    val currentCodeVerifiers = ArrayList<String>()
    val currentCodeChallenges = ArrayList<String>()

    fun generateVerifier(): String {
        val generatedVerifier = generateRandomString(64)
        currentCodeVerifiers.add(generatedVerifier)
        return generatedVerifier
    }

    fun generateChallenge(): String {
        return generateChallenge(generateVerifier())
    }

    fun generateChallenge(verifier: String): String {
        val hashedVerifier = verifier.hashS256()
        val generatedChallenge = hashedVerifier.base64encode()
        currentCodeChallenges.add(generatedChallenge)
        return generatedChallenge
    }
}