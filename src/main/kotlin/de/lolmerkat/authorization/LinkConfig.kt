package de.lolmerkat.authorization

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File

object LinkConfig {
    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class Data (
        // REQUIRED PARAMETER
        @EncodeDefault
        @SerialName("response_type")
        val responseType: String = "code",

        @EncodeDefault
        @SerialName("redirect_uri")
        val redirectUri: String = "http://127.0.0.1",

        // OPTIONAL PARAMETERS
        @EncodeDefault
        val state: String? = null,

        @EncodeDefault
        @SerialName("code_challenge_method")
        val codeChallengeMethod: String? = null,

        @EncodeDefault
        val scope: List<String> = emptyList()
    )
    private const val filePath: String = "./src/main/resources/config/link_properties.json"
    private val file = File(filePath)
    private val json: Json = Json { prettyPrint = true }

    init {
        if (!file.parentFile.exists())
            file.parentFile.mkdirs()

        if (!file.exists()) {
            file.createNewFile()
        }

        save()
    }

    val fileContent: String
        get() = file.readText()

    val data: Data
        get() = Json.decodeFromString<Data>(fileContent)

    fun save() {
        file.writeText(json.encodeToString(Data()))
    }
}