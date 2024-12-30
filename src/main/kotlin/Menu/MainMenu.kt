package Menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainMenu(
    HandleMenu: (String) -> Unit,
) {
    // Main menu
    AnimatedVisibility(
        visible = true,
        label = "Main menu",
        enter = slideInVertically(initialOffsetY = { fullHeight -> -fullHeight }),
        exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight }),

        ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("SHITHEAD")
                Button(onClick = {
                    HandleMenu("Game")
                }) {
                    Text("Game test")
                }
                Button(onClick = {
                    HandleMenu("Lobby")
                }) {
                    Text("Join a lobby")
                }
                Button(onClick = {
                    HandleMenu("Create")
                }) {
                    Text("Create a lobby")
                }
                Button(onClick = {
                    HandleMenu("Settings")
                }) {
                    Text("Settings")
                }
                Button(onClick = {
                    HandleMenu("Exit")
                }) {
                    Text("Exit")
                }
            }
        }
    }

}
