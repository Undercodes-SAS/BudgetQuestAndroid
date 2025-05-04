package net.undercodes.budgetquest.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nombre") val username: String,
    @SerializedName("clave") val password: String
)