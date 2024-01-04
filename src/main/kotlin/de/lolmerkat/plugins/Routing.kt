package de.lolmerkat.plugins

import de.lolmerkat.authorization.AuthState
import de.lolmerkat.authorization.AuthorizationResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.Exception

fun Application.configureRouting() {
    routing {
        get("/callback") {
            val params: Parameters = call.parameters
            call.respond("Code: ${params["code"]}")

            if (!AuthState.currentlyUsedStates.contains(AuthState(params["state"]!!)))
                throw Exception("State forgery was detected.")

            val response = AuthorizationResponse()
            response.state = params["state"]!!

            if (params.contains("code"))
                response.code = params["code"]

            if (params.contains("error"))
                response.error = params["error"]

            response.save()
        }

        //staticResources("/callback", "static")
    }
}
