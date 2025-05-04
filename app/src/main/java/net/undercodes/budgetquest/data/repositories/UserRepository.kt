package net.undercodes.budgetquest.data.repositories

import net.undercodes.budgetquest.data.ApiService
import net.undercodes.budgetquest.data.LoginRequest
import net.undercodes.budgetquest.data.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mechanical-jacki-asprinos-d8028e59.koyeb.app/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun login(username: String, password: String): retrofit2.Response<LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return apiService.login(loginRequest)
    }
}