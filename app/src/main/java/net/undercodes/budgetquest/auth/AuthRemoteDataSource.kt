package net.undercodes.budgetquest.data.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import net.undercodes.budgetquest.domain.User

interface AuthRemoteDataSource {
    suspend fun validateToken(authorization: String): User
}

class AuthRemoteDataSourceImpl(
    private val serverUrl: String,
    private val httpClient: HttpClient
) : AuthRemoteDataSource {
    override suspend fun validateToken(authorization: String): User {
        return httpClient.post("$serverUrl/login") {
            header("Authorization", authorization)
        }.body<User>()
    }
}