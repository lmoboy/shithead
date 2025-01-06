import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import game.ShitheadGame

@Composable
fun GameLayout(
    game: ShitheadGame
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Center pile
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (game.centerPile.isNotEmpty()) {
                Card(value = game.centerPile.last())
            }
        }

        // North player (top)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Hand(
                cards = game.northHand,
                hidden = game.currentPlayer != 2,
                handleClick = { value -> if (game.currentPlayer == 2) game.onCardClicked(value) }
            )
        }

        // South player (bottom)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Hand(
                cards = game.southHand,
                hidden = game.currentPlayer != 0,
                handleClick = { value -> if (game.currentPlayer == 0) game.onCardClicked(value) }
            )
        }

        // West player (left)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Hand(
                cards = game.westHand,
                vertical = true,
                hidden = game.currentPlayer != 1,
                handleClick = { value -> if (game.currentPlayer == 1) game.onCardClicked(value) }
            )
        }

        // East player (right)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Hand(
                cards = game.eastHand,
                vertical = true,
                hidden = game.currentPlayer != 3,
                handleClick = { value -> if (game.currentPlayer == 3) game.onCardClicked(value) }
            )
        }
    }
}
