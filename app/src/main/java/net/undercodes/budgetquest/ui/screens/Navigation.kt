package net.undercodes.budgetquest.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.undercodes.budgetquest.ui.screens.Login.Login
import net.undercodes.budgetquest.ui.screens.homeScreen.HomeScreen
import net.undercodes.budgetquest.ui.screens.Wallet.WalletScreen


@Composable
fun Navigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginScreen"){
        composable("loginScreen"){
            Login(
                modifier = modifier,
                onLoginSuccess = {
                    //navController.navigate("homeScreen")
                    navController.navigate("Wallet")
                }
            )
        }
        composable("homeScreen") {
            HomeScreen()
        }
        composable("Wallet") {
            WalletScreen()
        }
    }
}