import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Hand(
    cards: List<String>,
    hidden: Boolean = false,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        for (card in cards) {
            Box(modifier = Modifier.aspectRatio(1 / 2f).clip(RoundedCornerShape(5.dp)).background(Color.White)
                .drawBehind {
                    drawRect(
                        color = Color.Red,
                        size = Size(10.0f, 10.0f),
                    )
                }

            ) {

            }
        }
    }
}

