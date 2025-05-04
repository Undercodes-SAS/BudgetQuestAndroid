package net.undercodes.budgetquest.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.undercodes.budgetquest.ui.screens.Login.Login
import net.undercodes.budgetquest.ui.screens.homeScreen.HomeScreen
import net.undercodes.budgetquest.ui.screens.Wallet.WalletScreen
import net.undercodes.budgetquest.ui.screens.spentRegisterScreen.SpentRegisterScreen


@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()){

    NavHost(navController = navController, startDestination = "loginScreen"){
        composable("loginScreen"){
            Login(
                modifier = modifier,
                onLoginSuccess = {
                    navController.navigate("homeScreen")

                }
            )
        }
        composable("homeScreen") {
            HomeScreen()
        }

        composable("Wallet") {
            WalletScreen()
        }
        composable("registroGastoScreen/{gasto_texto}") { backStackEntry ->
            val gastoTexto = backStackEntry.arguments?.getString("gasto_texto")
            SpentRegisterScreen(gastoTexto = gastoTexto)

        }
    }
}