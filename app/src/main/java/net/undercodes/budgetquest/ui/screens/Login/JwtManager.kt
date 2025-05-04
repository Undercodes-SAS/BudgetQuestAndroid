package net.undercodes.budgetquest.ui.screens.Login
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class JwtManager(private val context: Context) {
     private val jwtPrefs = context.getSharedPreferences("jwt_prefs", Context.MODE_PRIVATE)
    private val userPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveJwt(jwt: String) {
        jwtPrefs.edit { putString("jwt_token", jwt) }
    }

    fun getJwt(): String? =
        jwtPrefs.getString("jwt_token", null)

    fun clearJwt() {
        jwtPrefs.edit { remove("jwt_token") }
    }

    fun saveUserId(id: String) {
        userPrefs.edit { putString("user_id", id) }
    }

    fun getUserId(): String? =
        userPrefs.getString("user_id", null)

    fun clearUserId() {
        userPrefs.edit { remove("user_id") }
    }


    fun decryptJwt(jwt: String): String {
        val parts = jwt.split('.')
        if (parts.size != 3) return "{}"

        return try {
            val decoded = Base64.decode(
                parts[1],
                Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
            )
            val payload = String(decoded, Charsets.UTF_8)
            val id = Json.parseToJsonElement(payload)
                .jsonObject["usuario"]
                ?.jsonPrimitive
                ?.content
                .orEmpty()

            id
        } catch (e: Exception) {
            "{}"
        }
    }
}