package Menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CreateLobbyMenu(
    HandleChange: (String) -> Unit
) {
    var LobbyCode by remember { mutableStateOf("") }
    var PrivateLobby by remember { mutableStateOf(false) }



    AnimatedVisibility(
        visible = true,
        label = "Create lobby",
        enter = slideInVertically(initialOffsetY = { fullHeight -> -fullHeight }),
        exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
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
                Text("SHITHEAD CREATE LOBBY")

                TextField(
                    label = { Text("Private Code") },
                    value = LobbyCode,
                    onValueChange = { LobbyCode = it },
                )
                Checkbox(
                    checked = PrivateLobby,
                    onCheckedChange = { PrivateLobby = !PrivateLobby },
                )
                Text("Private: $PrivateLobby")

                Button(
                    onClick = {
                        HandleChange("Loading")
                    }
                ) {
                    Text("Create")
                }

                Button(onClick = {
                    HandleChange("Main")
                }) {
                    Text("Back")
                }
            }

        }
    }
}