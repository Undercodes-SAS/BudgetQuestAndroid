package net.undercodes.budgetquest.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // Replace "login" with your actual endpoint
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}