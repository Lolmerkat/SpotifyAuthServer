package de.lolmerkat.authorization

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object AuthorizationResponse {
        @Serializable
        @OptIn(ExperimentalSerializationApi::class)
        object Data {
                var code: String? = null
                var error: String? = null
                @EncodeDefault
                var state: String = ""
        }

        private const val path = "./src/main/resources/response.json"
        private val file = File(path)
        private val json: Json = Json { prettyPrint = true }

        val fileContent: String
                get() = file.readText()

        val data: Data
                get() = Data

        init {
                if (!file.parentFile.exists())
                        file.parentFile.mkdirs()

                if (!file.exists())
                        file.createNewFile()
        }

        fun save() {
                file.writeText(json.encodeToString(Data))
        }
}