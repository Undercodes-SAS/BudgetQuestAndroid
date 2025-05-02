package net.undercodes.budgetquest.ui.components.login

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import net.undercodes.budgetquest.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}, enabled: Boolean = true
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {


        Box(
            modifier = Modifier.height(56.dp).fillMaxWidth(), contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(28.dp))
            )


            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.size(40.dp).absoluteOffset(x = (20).dp, y = (3).dp)

                ) {
                    Image(
                        painter = painterResource(R.drawable.isotipo),
                        contentDescription = "Icono decorativo",
                        modifier = Modifier.fillMaxSize()
                    )
                }


                Button(
                    onClick = onClick,
                    enabled = enabled,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .zIndex(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF1F1F1),
                        contentColor = Color(0xFF000000),
                    )
                ) {
                    Text(text)
                }


                Box(
                    modifier = Modifier.size(40.dp).absoluteOffset(x = (-20).dp, y = (3).dp)
                        .zIndex(0f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.isotipo),
                        contentDescription = "Icono decorativo",
                        modifier = Modifier.fillMaxSize().graphicsLayer(scaleX = -1f)
                    )
                }
            }
        }
    }
}