package de.lolmerkat

import de.lolmerkat.exceptions.IncompleteConfigException
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object CredentialProperties {
    @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
    @Serializable
    data class Data(
        @EncodeDefault
        var clientId: String? = null
    )

    private const val filePath: String = "./src/main/resources/config/credentials.json"
    private val file = File(filePath)
    private val json = Json { prettyPrint = true }

    init {
        if (!file.parentFile.exists())
            file.parentFile.mkdirs()

        if (!file.exists()) {
            file.createNewFile()
            file.writeText(json.encodeToString(Data()))
        }

        if (data.clientId == null)
            throw IncompleteConfigException("\n" +
                "----------------------------------------------\n" +
                "Missing credentials.\n " +
                "Please set the missing values in file:\n" +
                "$filePath\n" +
                "----------------------------------------------")
    }

    val fileContent: String
        get() = file.readText()

    val data: Data
        get() = Json.decodeFromString<Data>(fileContent)
}
