package de.lolmerkat.authorization

import de.lolmerkat.CredentialProperties
import io.ktor.http.*

object AuthorizationLink {
    private const val authUrl: String = "accounts.spotify.com/authorize/"
    var url: Url

    init {
        val linkConfigData = LinkConfig.data
        val credentials = CredentialProperties.data
        url = URLBuilder(
            protocol = URLProtocol.HTTPS,
            host = authUrl,
            parameters = Parameters.build {
                credentials.clientId?.let {
                    append("client_id", it)
                }
                append("response_type", linkConfigData.responseType)
                append("redirect_uri", linkConfigData.redirectUri)
                linkConfigData.state?.let { append("state", it) }
                linkConfigData.codeChallengeMethod?.let {
                    val codeChallenge: String = CodeChallenge.generateChallenge()
                    
                    append("code_challenge_method", it)
                    append("code_challenge", codeChallenge)
                }
                append("scope", linkConfigData.scope.toParamString())
                append("state", AuthState.generate())
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