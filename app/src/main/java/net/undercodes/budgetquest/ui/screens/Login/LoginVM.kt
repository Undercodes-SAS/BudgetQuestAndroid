package net.undercodes.budgetquest.ui.screens.Login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.undercodes.budgetquest.data.LoginResponse
import net.undercodes.budgetquest.data.repositories.UserRepository

class LoginViewModel(private val userRepository: UserRepository = UserRepository()) : ViewModel() {
    val loginResult: MutableState<LoginResult> = mutableStateOf(LoginResult.Idle)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginResult.value = LoginResult.Loading
            try {
                val response = userRepository.login(username, password)
                if (response.isSuccessful) {
                    loginResult.value = LoginResult.Success(response.body()!!)
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