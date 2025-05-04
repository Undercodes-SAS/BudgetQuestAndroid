package net.undercodes.budgetquest.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    private val apiService = RetrofitClient.getApiService()

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            apiService.login(request)
        }
    }
}