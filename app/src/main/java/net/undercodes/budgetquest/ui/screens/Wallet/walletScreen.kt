package net.undercodes.budgetquest.ui.screens.Wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WalletScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Sección de Total
            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                Text(
                    text = "Total:",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Text(
                    text = "$400.000",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Acción para agregar datos */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add data")
                }
            }

            // Sección de cuentas favoritas
            Column {
                Text(
                    text = "Favorite accounts",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Tabla de cuentas
                Column {
                    // Encabezados
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Bank", fontWeight = FontWeight.Bold)
                        Text("Total", fontWeight = FontWeight.Bold)
                    }

                    // Fila Nequi
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Nequi")
                        Text("$320.000")
                    }

                    // Fila Recoverable
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Bancolombia")
                        Text("$80.000")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedButton(
                    onClick = { /* Acción para agregar banco */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add bank")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletPreview() {
    MaterialTheme {
        WalletScreen()
    }
}