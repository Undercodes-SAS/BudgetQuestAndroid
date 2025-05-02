package net.undercodes.budgetquest.ui.screens.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.undercodes.budgetquest.ui.screens.Screen

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Screen{
        Scaffold { padding ->
            Text("Home Screen", modifier = modifier.padding(padding))
        }
    }
}