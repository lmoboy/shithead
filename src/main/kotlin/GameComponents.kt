import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Card(
    vertical: Boolean = false,
    index: Int = 0,
    value: String,
    hidden: Boolean = false,
    cardSize: Dp = 120.dp
) {
    val cardRatio = 1.33333f
    val delay = 100 * index
    var show by remember { mutableStateOf(false) }
    var globalPosition by remember { mutableStateOf(Offset.Zero) }
    AnimatedVisibility(
        visible = show,
        modifier = Modifier
            .absoluteOffset(x = if (index <= 5) 0.dp else -(10 * index).dp)
            .onGloballyPositioned { position -> globalPosition = position.positionInRoot() },
        label = "Main menu",
        exit = slideOut { fullSize ->
            IntOffset(
                -globalPosition.x.toInt() - (fullSize.width / 2) + (1280 / 2),
                -globalPosition.y.toInt() - (fullSize.height / 2) + (720 / 2)
            )
        }

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color.White)
                .size(
                    width = if (!vertical) cardSize else cardSize * cardRatio,
                    height = if (!vertical) cardSize * cardRatio else cardSize
                )
                .onPointerEvent(PointerEventType.Enter) {
                    if (!hidden)
                        Modifier.graphicsLayer { translationY = -20f }

                }
                .onPointerEvent(PointerEventType.Exit) {
                    if (!hidden)
                        Modifier.graphicsLayer { translationY = 0f }
                }
                .onClick {
                    if (!hidden)
                        show = false
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
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!hidden) {
                        Text(
                            text = value,
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
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
    cards: List<List<String>>
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Hand(
                vertical = true,
                cards = cards,
                player = 1
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Hand(

                cards = cards,
                player = 2
            )
            ThePile(cards = cards, player = 0)
            Hand(
                cards = cards,
                player = 0
            )
        }
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Hand(
                vertical = true,
                cards = cards,
                player = 3
            )
        }

    }
}


@Composable
fun Hand(
    vertical: Boolean = false,
    cards: List<List<String>>,
    player: Int,
) {
    if (!vertical) {
        Row(
            modifier = Modifier
                .wrapContentSize(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(10.dp),

            ) {
            for (card in cards) {
                Card(value = card[1], hidden = player != 0, index = cards.indexOf(card) * player)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(10.dp),

            ) {
            for (card in cards) {
                Card(vertical = true, value = card[1], hidden = player != 0, index = cards.indexOf(card) * player)
            }
        }
    }
}


@Composable
fun ThePile(
    cards: List<List<String>>,
    player: Int
) {
    Box(
        modifier = Modifier
            .rotate(90f * player)
            .wrapContentSize(Alignment.BottomCenter)
            .padding(10.dp),

        ) {
        Card(value = cards[cards.size - 1][1], hidden = player != 0, index = cards.size)

    }
}
