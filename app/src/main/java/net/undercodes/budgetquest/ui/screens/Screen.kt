package net.undercodes.budgetquest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.undercodes.budgetquest.ui.theme.BudgetQuestAndroidTheme

@Composable
fun Screen (modifier: Modifier = Modifier, content: @Composable () -> Unit) {

        Surface(
            modifier = modifier.fillMaxSize(),
            content = content
        )

}