package net.undercodes.budgetquest.ui.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import net.undercodes.budgetquest.intentManager.handleIntent

@Composable
fun AppNavigationWithIntent(intent: Intent?, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Use a more stable key that includes only the intent data/action that matters
    // not the entire intent object which might change identity
    LaunchedEffect(intent?.dataString, intent?.action) {
        if (intent != null) {
            handleIntent(intent, navController)
        }
    }

    // Apply the modifier to the Navigation composable
    Navigation(
        navController = navController,
        modifier = modifier
    )
}