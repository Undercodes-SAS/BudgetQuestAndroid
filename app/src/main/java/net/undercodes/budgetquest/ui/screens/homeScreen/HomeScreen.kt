package net.undercodes.budgetquest.ui.screens.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import net.undercodes.budgetquest.R
import net.undercodes.budgetquest.ui.screens.Screen
import kotlin.collections.forEachIndexed



@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Screen {
        FinanceDashboard(modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FinanceDashboard(modifier: Modifier = Modifier) {
    val quests = listOf(
        Quest("Invierte 30 mil en un fondo de inversión de tu preferencia", false),
        Quest("No gastes más de 20 mil en comida chatarra", false)
    )

    val tips = listOf(
        "Empieza tu vida crediticia pero paga todo a 1 cuota",
        "Diversificar las inversiones es la opción más viable."
    )

    val oddities = listOf(
        "¿Sabías que ******* tiene una tarjeta de crédito para Jóvenes?",
        "Uno de los fondos de inversión con mayor rentabilidad es *******"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo de la app",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(148.dp)
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.perfil_defecto),
                        contentDescription = "Foto de perfil",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(148.dp)
                            .padding(8.dp)
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { WelcomeSection("Cristhian") }
            item { OdditiesSection(oddities) }
            item { QuestsSection(quests) }
            item { TipsSection(tips) }
        }
    }
}

@Composable
private fun WelcomeSection(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome \n" +
                        "to your finance,",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
private fun OdditiesSection(oddities: List<String>) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Oddities",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            oddities.forEach { tip ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.heightIn(min = TextUnit.Unspecified.value.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.isotipo),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(MaterialTheme.typography.bodyMedium.fontSize.value.dp + 8.dp)
                    )

                    Text(
                        text = tip,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun QuestsSection(quest: List<Quest>) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Quests",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            quest.forEachIndexed { index, currentQuest ->
                var checkedState by remember { mutableStateOf(currentQuest.completed) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                   Checkbox(

                        checked = currentQuest.completed,
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.align(Alignment.Top),

                        onCheckedChange = { isChecked ->
                           checkedState = isChecked
                        }
                    )
                    Text(text = currentQuest.description, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun TipsSection(tips: List<String>) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Tips",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            tips.forEach { tip ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.heightIn(min = TextUnit.Unspecified.value.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.tips_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(MaterialTheme.typography.bodyMedium.fontSize.value.dp + 8.dp)
                    )

                    Text(
                        text = tip,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


data class Quest(val description: String, val completed: Boolean)

@Preview(showBackground = true)
@Composable
fun PreviewFinanceDashboard() {
    MaterialTheme {
        FinanceDashboard()
    }
}