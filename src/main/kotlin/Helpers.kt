import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TranslateYAnimated(
    targetOffset: Dp,
    durationMillis: Int = 500,
    delayMillis: Int = 0,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset.value,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis
            ),
            repeatMode = RepeatMode.Reverse
        )

    )

    modifier
        .offset(y = offset.dp)
}