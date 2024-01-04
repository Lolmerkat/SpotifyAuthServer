package de.lolmerkat.authorization

import io.ktor.http.*

object AuthorizationLink {
    private const val clientId: String = "607c609eb4114d729991b1d7f25d380d"
    private const val authUrl: String = "accounts.spotify.com/authorize/"

    init {
        val linkConfigData = LinkConfig.data
        val url = URLBuilder(
            protocol = URLProtocol.HTTPS,
            host = authUrl,
            parameters = Parameters.build {
                append("client_id", clientId)
                append("response_type", linkConfigData.responseType)
                append("redirect_uri", linkConfigData.redirectUri)
                linkConfigData.state?.let { append("state", it) }
                linkConfigData.codeChallengeMethod?.let {
                    val codeChallenge: String = "" //TODO: GENERATE
                    
                    append("code_challenge_method", it)
                    append("code_challenge", codeChallenge)
                }
                append("scope", linkConfigData.scope.toParamString())
            }
        ).build()
    }
}

private fun List<String>.toParamString(): String {
    var paramString = ""

    for (str in this) {
        paramString += "$str "
    }

    return paramString
}