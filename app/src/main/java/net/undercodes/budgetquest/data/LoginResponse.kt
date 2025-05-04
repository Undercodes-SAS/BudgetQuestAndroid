package net.undercodes.budgetquest.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null
)