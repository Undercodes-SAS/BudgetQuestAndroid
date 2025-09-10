package net.undercodes.budgetquest.ui.components.speechButton

import SpeechRecognizerHelper
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SpeechButton(viewModel: SpeechButtonVM = viewModel()) {

    val context = LocalContext.current
    val recognizerHelper = remember {
        SpeechRecognizerHelper(context) { spokenText ->
            viewModel.updateText(spokenText)
        }
    }

    IconButton(onClick = {
        recognizerHelper.startListening()
    }) {
        Icon(
            imageVector = Icons.Default.Mic,
            contentDescription = "Hablar",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )
    }

    // Solicitud de permisos
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                1
            )
        }
    }
}
