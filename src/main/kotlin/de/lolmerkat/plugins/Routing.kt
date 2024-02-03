package de.lolmerkat.plugins

import de.lolmerkat.authorization.AuthState
import de.lolmerkat.authorization.AuthorizationResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/callback", "static", index = "index.html") {
            modify { url, call ->
                val params: Parameters = call.parameters
                val requestState = params["state"]!!
                //call.respond("Code: ${params["code"]}")

                println("AUTH STATES IN USE ==> ${AuthState.currentlyUsedStates}")
                if (!AuthState.currentlyUsedStates.contains(requestState))
                    throw Exception("Request Cancelled, State forgery was detected.")

                val response = AuthorizationResponse
                val responseData = response.data

                responseData.state = params["state"]!!

                if (params.contains("code"))
                    responseData.code = params["code"]

                if (params.contains("error"))
                    responseData.error = params["error"]

                response.save(responseData)
                AuthState.currentlyUsedStates.remove(requestState)
            }
        }
    }
}
