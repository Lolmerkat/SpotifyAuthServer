package de.lolmerkat.authorization

import de.lolmerkat.extensions.generateRandomString

class AuthState(val value: String) {
    companion object {
        val currentlyUsedStates = ArrayList<AuthState>()

        fun generate(): AuthState {
            val generatedState = AuthState(generateRandomString(64))
            currentlyUsedStates.add(generatedState)
            return generatedState
        }
    }
}