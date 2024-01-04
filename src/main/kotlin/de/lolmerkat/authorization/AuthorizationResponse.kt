package de.lolmerkat.authorization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.PrintStream

@Serializable
data class AuthorizationResponse(
        var code: String? = null,
        var error: String? = null,
        var state: String = ""
) {
        fun save() {
                val file = ResponseFile
                ResponseFile.write(Json.encodeToString(value = this))
        }
}

object ResponseFile {
        private const val path = "./src/main/resources/response.json"
        private val file = File(path)
        private val writer = PrintStream(file)

        init {
                if (!file.parentFile.exists())
                        file.parentFile.mkdirs()

                if (!file.exists())
                        file.createNewFile()
        }

        fun write(content: String) {
                writer.println(content)
                writer.flush()
                writer.close()
        }
}