package net.undercodes.budgetquest.ui.screens.Wallet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.undercodes.budgetquest.ui.components.speechButton.SpeechButton
import net.undercodes.budgetquest.ui.components.speechButton.SpeechButtonVM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray

data class Cuenta(
    val id: Int,
    val nombre: String,
    val saldo: Double,
    val usuarioId: Int
)

suspend fun fetchCuentasFavoritas(usuarioId: Int): List<Cuenta> {
    val client = OkHttpClient()
    val url = "https://mechanical-jacki-asprinos-d8028e59.koyeb.app/cuenta/cuentas/$usuarioId"
    val request = Request.Builder().url(url).get().build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val jsonArray = JSONArray(response.body?.string())
            val cuentas = mutableListOf<Cuenta>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                cuentas.add(
                    Cuenta(
                        id = jsonObject.getInt("id"),
                        nombre = jsonObject.getString("nombre"),
                        saldo = jsonObject.getDouble("saldo"),
                        usuarioId = jsonObject.getInt("usuarioId")
                    )
                )
            }
            cuentas
        } else {
            emptyList()
        }
    }
}

@Composable
fun WalletScreen(viewModel: SpeechButtonVM = viewModel(), usuarioId: Int) {
    var showDialog by remember { mutableStateOf(false) }
    val textColor = Color(0xFF08415C)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Título central grande
            Text(
                text = "Wallet",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            // Sección de Total
            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                Text(
                    text = "Total:",
                    fontSize = 18.sp,
                    color = textColor
                )
                Text(
                    text = "$400.000",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Acción para agregar datos */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2936))
                ) {
                    Text("Add data")
                }
            }

            // Sección de cuentas favoritas
            FavoriteAccountsSection(usuarioId, textColor)

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para crear transacciones
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2936))
            ) {
                Text("Create Transaction")
            }
            SpeechButton(viewModel)
            val spokenText by viewModel.spokenText
            Text(
                text = spokenText,
                modifier = Modifier.fillMaxWidth(),
                color = textColor
            )
        }
    }

    // Ventana emergente con animación
    AnimatedVisibility(visible = showDialog) {
        TransactionDialog(onDismiss = { showDialog = false }, textColor = textColor)
    }
}

@Composable
fun FavoriteAccountsSection(usuarioId: Int, textColor: Color) {
    val cuentas by produceState<List<Cuenta>>(initialValue = emptyList(), usuarioId) {
        value = fetchCuentasFavoritas(usuarioId)
    }

    Column {
        Text(
            text = "Favorite accounts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cuentas.isEmpty()) {
            Text("No favorite accounts found.", color = textColor)
        } else {
            cuentas.forEach { cuenta ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(cuenta.nombre, fontWeight = FontWeight.Bold, color = textColor)
                    Text("$${cuenta.saldo}", fontWeight = FontWeight.Bold, color = textColor)
                }
            }
        }
    }
}

@Composable
fun TransactionDialog(onDismiss: () -> Unit, textColor: Color) {
    var tipo by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var cuentaId by remember { mutableStateOf("") }
    var cuentaDestinoId by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create Transaction", color = textColor) },
        text = {
            Column {
                OutlinedTextField(
                    value = tipo,
                    onValueChange = { tipo = it },
                    label = { Text("Tipo", color = textColor) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = monto,
                    onValueChange = { monto = it },
                    label = { Text("Monto", color = textColor) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción", color = textColor) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha", color = textColor) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = cuentaId,
                    onValueChange = { cuentaId = it },
                    label = { Text("Cuenta ID", color = textColor) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = cuentaDestinoId,
                    onValueChange = { cuentaDestinoId = it },
                    label = { Text("Cuenta Destino ID", color = textColor) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2936)),
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        val client = OkHttpClient()
                        val json = """
                            {
                                "tipo": "$tipo",
                                "monto": ${monto.toIntOrNull() ?: 0},
                                "descripcion": "$descripcion",
                                "fecha": "$fecha",
                                "cuentaId": ${cuentaId.toIntOrNull() ?: 0},
                                "cuentaDestinoId": ${cuentaDestinoId.toIntOrNull() ?: 0}
                            }
                        """.trimIndent()
                        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
                        val request = Request.Builder()
                            .url("https://mechanical-jacki-asprinos-d8028e59.koyeb.app/transacciones/crear")
                            .post(requestBody)
                            .build()
                        client.newCall(request).execute()
                    }
                    onDismiss()
                }
            ) {
                Text("Submit")
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2936)),
                onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}