import Menu.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App() {


    var HandList by remember { mutableStateOf(listOf<String>("4", "2", "J", "Q", "K")) }
    var PrivateLobby by remember { mutableStateOf(false) }
    var LobbyCode by remember { mutableStateOf("") }

    var CurrentMenu by remember { mutableStateOf("Main") }

    MaterialTheme {


        //Menu handler
        when (CurrentMenu) {
            "Main" -> {
                MainMenu(
                    { CurrentMenu = it }
                )
            }

            "Settings" -> {
                SettingsMenu(
                    { CurrentMenu = it }
                )
            }

            "Create" -> {
                CreateLobbyMenu(
                    { CurrentMenu = it }
                )
            }

            "Lobby" -> {
                LobbyMenu(
                    { CurrentMenu = it }
                )
            }

            "Game" -> {
                GameMenu(
                )
            }

            "Loading" -> {
                LoadingMenu(
                    { CurrentMenu = it }
                )
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "SHITHEAD", icon = null
    ) {
        App()
    }
}
