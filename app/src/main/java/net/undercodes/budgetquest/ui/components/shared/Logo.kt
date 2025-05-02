package net.undercodes.budgetquest.ui.components.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import net.undercodes.budgetquest.R


@Composable
fun Logo(modifier: Modifier = Modifier) {
    val painterLogo = painterResource(R.drawable.logo)
    Box(modifier) {
        Image(painterLogo, contentDescription = "Logo")
    }
}
