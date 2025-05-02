package net.undercodes.budgetquest.ui.components.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import net.undercodes.budgetquest.R


@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String = "",
    isPassword: Boolean = false
) {
    // State to track password visibility
    val passwordVisible = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp, start = 60.dp)
            )
        }

        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(28.dp))
            )


            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Contenedor del ícono izquierdo
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .absoluteOffset(x = (10).dp, y = (3).dp)
                        .zIndex(1f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.isotipo),
                        contentDescription = "Icono decorativo",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Input de texto
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .zIndex(0f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(color = Color(0xFFF1F1F1))
                            .height(30.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center  // Center the content vertically
                    ) {
                        BasicTextField(
                            value = text,
                            onValueChange = onTextChange,
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Gray,
                            ),
                            visualTransformation = if (isPassword && !passwordVisible.value)
                                PasswordVisualTransformation() else VisualTransformation.None,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }

                    // Password visibility toggle (keep this part the same)
                    if (isPassword) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                                .size(24.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFEEEEEE))
                                .clickable { passwordVisible.value = !passwordVisible.value }
                        ) {
                            Icon(
                                imageVector = if (passwordVisible.value)
                                    Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = "Toggle password visibility",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }


                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .absoluteOffset(x = (-10).dp, y = (3).dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.isotipo),
                        contentDescription = "Icono decorativo",
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(scaleX = -1f)
                    )
                }
            }
        }
    }
}