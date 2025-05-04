package net.undercodes.budgetquest.ui.screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.undercodes.budgetquest.ui.components.login.CustomButton
import net.undercodes.budgetquest.ui.components.login.CustomInputField
import net.undercodes.budgetquest.ui.components.shared.Logo
import net.undercodes.budgetquest.ui.screens.Screen

@Composable
fun Login(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isFormValid = username.isNotEmpty() && password.isNotEmpty()

    val loginResult by loginViewModel.loginResult

    LaunchedEffect(loginResult) {
        when (loginResult) {
            is LoginResult.Success -> {
                onLoginSuccess() // Navigate on successful login
            }
            is LoginResult.Error -> {
                println("Login Error: ${(loginResult as LoginResult.Error).message}")
            }

            else -> {}
        }
    }

    Screen(modifier) {
        Scaffold { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Logo(Modifier.padding(top = 50.dp).width(320.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomInputField(
                        text = username, onTextChange = { username = it }, label = "Usuario"
                    )
                    CustomInputField(
                        text = password,
                        onTextChange = { password = it },
                        label = "Contraseña",
                        isPassword = true
                    )
                    Spacer(modifier = Modifier.size(75.dp))
                    when (loginResult) {
                        LoginResult.Loading -> Text("Loading...")
                        is LoginResult.Error -> Text((loginResult as LoginResult.Error).message)
                        else -> {}
                    }
                    CustomButton(
                        text = "Login",
                        onClick = { loginViewModel.login(username, password) },
                        enabled = isFormValid
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                }
            }
        }
    }
}