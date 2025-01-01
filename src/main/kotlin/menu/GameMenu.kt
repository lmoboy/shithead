package menu

import Table
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GameMenu(
    showMenu: Boolean,

    HandList: List<List<String>> =
        listOf(
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "4"),
            listOf("Hearts", "2"),
            listOf("Spades", "K")
        )
) {
    AnimatedVisibility(
        visible = showMenu,
        enter = slideInVertically(initialOffsetY = { fullHeight -> -fullHeight }),
        exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Table(
                cards = HandList
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            }
        }
    }
}