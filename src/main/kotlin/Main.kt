import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import menu.*
import kotlin.system.exitProcess


@Composable
@Preview
fun App() {
    var CurrentMenu by remember { mutableStateOf("Main") }
    var CurrentBackground by remember { mutableStateOf("MainBackground.jpg") }

    MaterialTheme {
        Image(
            painter = painterResource(CurrentBackground),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()

        )

        //Menu handler
        when (CurrentMenu) {
            "Main" -> {
                CurrentBackground = "MainBackground.jpg"
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
                CurrentBackground = "GameBackground.png"
                GameMenu(
                    true,
                )
            }

            "Loading" -> {
                LoadingMenu(
                    { CurrentMenu = it }
                )
            }

            "Exit" -> {
                exitProcess(1)
            }
        }
    }
}

fun main() = application {
    Window(
        undecorated = true,
        transparent = true,
        resizable = false,
        state = rememberWindowState(position = WindowPosition(Alignment.Center), width = 1280.dp, height = 720.dp),
        onCloseRequest = ::exitApplication,
        title = "SHITHEAD",
        icon = painterResource("icon.png")
    ) {
        App()
    }
}
