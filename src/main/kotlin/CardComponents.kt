import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Card(
    value: Int,
    hidden: Boolean = false,
    vertical: Boolean = false,
    handleClick: (value: Int) -> Unit = {}
) {
    var isHovered by remember { mutableStateOf(false) }

    val hoverOffset = animateFloatAsState(
        targetValue = if (isHovered) -40f else 0f,
        animationSpec = tween(
            durationMillis = 200,
            easing = EaseInOutCirc
        )
    )

    Box(
        modifier = Modifier
            .onClick {
                if (!hidden) {
                    handleClick(value)
                }
            }
            .zIndex(if (isHovered) 1f else 0f)
            .graphicsLayer {
                translationY = hoverOffset.value
            }
            .then(
                if (hidden) Modifier
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .border(4.dp, Color.Black, RoundedCornerShape(10.dp))
                else Modifier
                    .onPointerEvent(PointerEventType.Enter) { isHovered = true }
                    .onPointerEvent(PointerEventType.Exit) { isHovered = false }
            )
            .then(
                if (vertical) Modifier.size(160.dp, 120.dp)
                else Modifier.size(120.dp, 160.dp)
            )

    ) {
        Image(
            painter = painterResource(
                if (hidden) "game/background.png"
                else "game/cards/red_card.jpg"
            ),
            contentDescription = if (hidden) "Hidden card" else "Card $value",
            modifier = Modifier.then(
                if (vertical) {
                    Modifier.size(160.dp, 120.dp)
                } else Modifier.size(120.dp, 160.dp)
            ),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .matchParentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!hidden) {
                Column {
                    Text(
                        text = value.toString(),
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.then(
                            if (vertical)
                                Modifier.rotate(90f)
                            else Modifier
                        )
                    )
                    Text(
                        text = value.toString(),
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.then(
                            if (vertical)
                                Modifier.rotate(270f)
                            else Modifier.rotate(180f)
                        )
                    )

                }
            }
        }
    }
}

@Composable
fun Hand(
    cards: List<Int>,
    vertical: Boolean = false,
    hidden: Boolean = false,
    handleClick: (value: Int) -> Unit
) {
    val cardWidth = 120.dp
    val cardHeight = 160.dp
    val maxContainerSize = 600.dp
    val baseSpacing = 10.dp

    // Calculate spacing based on number of cards
    val spacing = when {
        cards.size <= 4 -> baseSpacing  // Normal spacing for 4 or fewer cards
        cards.size == 5 -> 0.dp         // No extra spacing at 5 cards
        else -> {
            // Progressive negative spacing starting from 6 cards
            // Each additional card adds -8.dp of spacing
            (-cardWidth / (cards.size - 1) * (cards.size - 5))
        }
    }

    if (!vertical) {
        Row(
            modifier = Modifier
                .width(maxContainerSize),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            cards.forEach { value ->
                Card(
                    value = value,
                    hidden = hidden,
                    vertical = false,
                    handleClick = handleClick
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .height(maxContainerSize),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            cards.forEach { value ->
                Card(
                    value = value,
                    hidden = hidden,
                    vertical = true,
                    handleClick = handleClick
                )
            }
        }
    }
}
