package net.undercodes.budgetquest.ui.components.login

import android.graphics.drawable.Drawable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .zIndex(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF1F1F1),
            contentColor = Color(0xFF000000),
        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}