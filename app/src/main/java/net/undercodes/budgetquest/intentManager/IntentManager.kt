package net.undercodes.budgetquest.intentManager

import android.content.Intent
import android.net.Uri
import androidx.core.bundle.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import net.undercodes.budgetquest.R


// Esta función debe recibir un NavController desde la actividad o componente donde se llama
fun handleIntent(intent: Intent?, navController: NavController) {
    val texto = intent?.data?.getQueryParameter("texto_gasto") ?: return
    navController.navigate("registroGastoScreen/${Uri.encode(texto)}") {
        launchSingleTop = true
        restoreState = true
    }
}