package net.undercodes.budgetquest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.undercodes.budgetquest.data.Screens
import net.undercodes.budgetquest.ui.components.shared.CustomBottomBar
import net.undercodes.budgetquest.ui.screens.Navigation
@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute != "loginScreen") { // Oculta la BottomBar en la pantalla de login
                CustomBottomBar(
                    navController = navController,
                    screens = listOf(Screens.Wallet, Screens.Home) // Lista de pantallas
                )
            }
        }
    ) { padding ->
        Navigation(
            modifier = modifier.padding(padding),
            navController = navController
        )
    }
}