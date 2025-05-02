package net.undercodes.budgetquest.domain

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: String,
    val token: String,
    val email: String,
    val name: String,
    val pictureUrl: String
)