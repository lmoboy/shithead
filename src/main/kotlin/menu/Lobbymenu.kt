package menu

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
import kotlinx.coroutines.delay

@Composable
fun LobbyMenu(

    HandleBack: (String) -> Unit
) {
    var ShouldShow by remember { mutableStateOf(false) }
    var LobbyCode by remember { mutableStateOf("") }
    var PrivateLobby by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = ShouldShow,
        label = "Join lobby",
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
                Text("SHITHEAD JOIN LOBBY")
                Row {
                    TextField(
                        label = { Text("Lobby Code") },
                        value = LobbyCode,
                        onValueChange = { LobbyCode = it },
                        enabled = PrivateLobby
                    )
                    Checkbox(
                        checked = PrivateLobby,
                        onCheckedChange = { PrivateLobby = !PrivateLobby },
                    )
                    Text("Private: $PrivateLobby")
                }

                Column {
                    Row {
                        Text("Lobby Code: $LobbyCode")
                        Button(onClick = {
                            HandleBack("Loading")
                        }) {
                            Text("Join")
                        }
                    }
                    Row {
                        Text("Lobby Code: $LobbyCode")
                        Button(onClick = {
                            HandleBack("Loading")
                        }) {
                            Text("Join")
                        }
                    }
                    Row {
                        Text("Lobby Code: $LobbyCode")
                        Button(onClick = {
                            HandleBack("Loading")
                        }) {
                            Text("Join")
                        }
                    }
                    Row {
                        Text("Lobby Code: $LobbyCode")
                        Button(onClick = {
                            HandleBack("Loading")
                        }) {
                            Text("Join")
                        }
                    }
                }
                Button(onClick = {
                    HandleBack("Main")
                }) {
                    Text("Back")
                }
            }

        }
    }

    LaunchedEffect(Unit) {
        delay(1)
        ShouldShow = true
    }
}
