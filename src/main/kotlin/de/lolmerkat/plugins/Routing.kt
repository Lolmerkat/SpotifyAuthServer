package de.lolmerkat.plugins

import de.lolmerkat.authorization.AuthState
import de.lolmerkat.authorization.AuthorizationResponse

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        //get("/callback") {
        //    val params: Parameters = call.parameters
        //    //call.respond("Code: ${params["code"]}")
//
        //    if (!AuthState.currentlyUsedStates.contains(AuthState(params["state"]!!)))
        //        throw Exception("State forgery was detected.")
//
        //    val response = AuthorizationResponse()
        //    response.state = params["state"]!!
//
        //    if (params.contains("code"))
        //        response.code = params["code"]
//
        //    if (params.contains("error"))
        //        response.error = params["error"]
//
        //    response.save()
        //}



        staticResources("/callback", "static") {
            enableAutoHeadResponse()

            get {
                val params: Parameters = call.parameters
                //call.respond("Code: ${params["code"]}")

                if (!AuthState.currentlyUsedStates.contains(AuthState(params["state"]!!)))
                    throw Exception("State forgery was detected.")

                val response = AuthorizationResponse
                val responseData = response.data

                responseData.state = params["state"]!!

                if (params.contains("code"))
                    responseData.code = params["code"]

                if (params.contains("error"))
                    responseData.error = params["error"]

                response.save()
            }
        }
    }
}
