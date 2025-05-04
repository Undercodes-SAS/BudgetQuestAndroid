package net.undercodes.budgetquest.ui.screens.Login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.undercodes.budgetquest.data.LoginResponse
import net.undercodes.budgetquest.data.repositories.UserRepository
import android.app.Application
class LoginViewModel(
    application: Application,
    private val userRepository: UserRepository = UserRepository()
) : AndroidViewModel(application) {
    val loginResult: MutableState<LoginResult> = mutableStateOf(LoginResult.Idle)
    val jwtManager = JwtManager(context = getApplication())

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginResult.value = LoginResult.Loading
            try {
                val response = userRepository.login(username, password)
                if (response.isSuccessful) {
                    val body: LoginResponse = response.body()!!
                    jwtManager.saveJwt(body.token.toString())
                    val userId = jwtManager.decryptJwt(body.token.toString())
                    jwtManager.saveUserId(userId)
                    loginResult.value = LoginResult.Success(body)
                } else {
                    loginResult.value = LoginResult.Error("Login failed")
                }
            } catch (e: Exception) {
                loginResult.value = LoginResult.Error("Network error")
            }
        }
    }
}

sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    data class Success(val loginResponse: LoginResponse) : LoginResult()
    data class Error(val message: String) : LoginResult()
}