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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.undercodes.budgetquest.R

import net.undercodes.budgetquest.ui.components.login.CustomButton
import net.undercodes.budgetquest.ui.components.login.CustomInputField
import net.undercodes.budgetquest.ui.components.login.SocialLoginButton
import net.undercodes.budgetquest.ui.components.shared.Logo
import net.undercodes.budgetquest.ui.screens.Screen

@Composable
fun Login(modifier: Modifier = Modifier, onLoginClick: () -> Unit = {}) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isFormValid = username.isNotEmpty() && password.isNotEmpty()

    Screen(modifier) {
        Scaffold { padding ->
            Column(
                Modifier.fillMaxSize().background(color = Color.White).padding(padding),
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
                    CustomButton(text = "Login", onClick = onLoginClick, enabled = isFormValid)
                    Spacer(modifier = Modifier.size(30.dp))
//                    SocialLoginButton(
//                        text = "Iniciar sesión con Google",
//                        icon = R.drawable.google_logo
//                    )
                }

            }
        }
    }
}





