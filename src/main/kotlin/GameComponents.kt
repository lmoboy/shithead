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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import kotlin.math.round


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Card(
    index: Int = 0,
    value: String,
    hidden: Boolean = false,
    cardSize: Dp = 120.dp
) {
    val cardRatio = 1.33333f
    val delay = index * 100
    var show by remember { mutableStateOf(false) }
    var globalPosition by remember { mutableStateOf(Offset.Zero) }
    AnimatedVisibility(
        visible = show,
        modifier = Modifier
            .onGloballyPositioned { position -> globalPosition = position.positionInRoot() },
        label = "Main menu",
        exit = slideOut { fullSize ->
            IntOffset(
                -globalPosition.x.toInt() - (fullSize.width / 2) + (1280 / 2),
                round(-fullSize.height - cardSize.value * 1.3333f).toInt()
            )
        }

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color.White)
                .size(width = cardSize, height = cardSize * cardRatio)
                .onClick { show = false }
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
fun Hand(
    cards: List<List<String>>,
    player: Int,
    modifier: Modifier? = null
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .rotate(90f * player)
            .wrapContentSize(Alignment.BottomCenter)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        Text(player.toString())
        for (card in cards) {
            Card(value = card[1], hidden = player != 0, index = cards.indexOf(card))
        }
    }
}


@Composable
fun ThePile(
    cards: List<List<String>>,
    player: Int
) {

}
