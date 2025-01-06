import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import game.ShitheadGame
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Card(
    vertical: Boolean = false,
    index: Int = 0,
    spacing: Int = 0,
    value: Int = 1,
    hidden: Boolean = false,
    cardSize: Dp = 120.dp,
    handleClick: (value: Int) -> Unit = {}
) {
    var isHovered by remember { mutableStateOf(false) }

    val cardRatio = 1.33333f
    val delay = 100 * index
    var show by remember { mutableStateOf(false) }
    var globalPosition by remember { mutableStateOf(Offset.Zero) }


    var hoverOffsetY = animateFloatAsState(
        targetValue = if (isHovered) -40f else -0f,

        animationSpec = tween(
            durationMillis = 200,
            easing = EaseInOutCirc
        )

    )

    AnimatedVisibility(
        visible = show,
        modifier = Modifier
            .zIndex(if (isHovered) 10f else 0f)
            .offset {
                IntOffset(
                    x = if (!vertical) (index - 1) * -spacing * 10 else 0,
                    y = if (vertical) (index - 1) * -spacing * 5 else 0
                )
            }
            .graphicsLayer {
                this.translationY = hoverOffsetY.value
            }
            .onPointerEvent(PointerEventType.Enter) {
                if (!hidden)
                    isHovered = true

            }
            .onPointerEvent(PointerEventType.Exit) {
                if (!hidden)
                    isHovered = false
            }
            .onGloballyPositioned { position ->
                globalPosition = position.positionInRoot()
            },

        exit = slideOut { fullSize ->
            IntOffset(
                -globalPosition.x.toInt() - (fullSize.width / 2) + (1280 / 2),
                -globalPosition.y.toInt() - (fullSize.height / 2) + (720 / 2)
            )
        }

    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.White, RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color.Black)
                .size(
                    width = if (!vertical) cardSize else cardSize * cardRatio,
                    height = if (!vertical) cardSize * cardRatio else cardSize
                )

                .onClick {
                    if (!hidden) {
                        isHovered = false
                        show = false
                    }
                }
        ) {
            if (hidden) {
                Image(
                    painter = painterResource("game/background.png" /*else "game/cards/$suit.png"*/),
                    contentScale = ContentScale.Crop,
                    contentDescription = "suit",
                )
            } else {
                Image(
                    painter = painterResource("game/cards/red_card.jpg" /*else "game/cards/$suit.png"*/),
                    contentScale = ContentScale.Crop,
                    contentDescription = "card",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

            }

        }

    }
    LaunchedEffect(Unit) {
        delay(delay.toLong())
        show = true
    }
}

@Composable
fun Table(
    cards: List<Int>
) {
    var game by remember { mutableStateOf(ShitheadGame()) }
    GameLayout(
        game = game
    )
}

@Composable
fun Hand(
    vertical: Boolean = false,
    cards: List<Int>,
    player: Int,
) {
    val cardWidth = 120.dp
    val cardHeight = (120 * 1.33333f).dp
    val maxCards = 15
    val cardSpacing = if (cards.size > 3) -(cardWidth * 0.7f) else (-20).dp

    if (!vertical) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .border(1.dp, Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .width(cardWidth + ((cards.size.coerceAtMost(maxCards) - 1) * cardSpacing))
                    .border(10.dp, Color.Green),
                horizontalArrangement = Arrangement.spacedBy(cardSpacing),
            ) {
                cards.mapIndexed { index, card ->
                    Card(
                        value = card,
                        hidden = player != 0,
                        spacing = cards.size,
                        index = index
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight + ((cards.size.coerceAtMost(maxCards) - 1) * cardSpacing))
                .border(1.dp, Color.Green)
        ) {
            Column(
                modifier = Modifier.matchParentSize(),
                verticalArrangement = Arrangement.spacedBy(cardSpacing),
            ) {
                cards.mapIndexed { index, card ->
                    Card(
                        vertical = true,
                        value = card,
                        hidden = player != 0,
                        spacing = cards.size,
                        index = index
                    )
                }
            }
        }
    }
}

@Composable
fun ThePile(
    cards: List<Int>,
    player: Int
) {
    Box(
        modifier = Modifier
            .rotate(90f * player)
            .wrapContentSize(Alignment.BottomCenter)
            .padding(10.dp),

        ) {
        Card(value = cards[cards.size - 1], hidden = false, spacing = 0)

    }
}
