package de.lolmerkat.authorization

import de.lolmerkat.extensions.generateRandomString

object AuthState {
    val currentlyUsedStates = ArrayList<String>()

    fun generate(): String{
        val generatedState = generateRandomString(64)
        currentlyUsedStates.add(generatedState)
        return generatedState
    }
}