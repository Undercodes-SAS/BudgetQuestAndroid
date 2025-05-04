package net.undercodes.budgetquest.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val icon: ImageVector) {

    object Home : Screens("homeScreen", Icons.Filled.Home)
    object Wallet : Screens("Wallet/1",Icons.Filled.Wallet)

    object Settings : Screens("settings", Icons.Filled.Settings)
}