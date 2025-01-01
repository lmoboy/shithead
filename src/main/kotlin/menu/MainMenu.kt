package menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun MainMenu(
    HandleMenu: (String) -> Unit,
) {

    var laughing by remember { mutableStateOf(false) }

    var animationDuration = 200

    var laughMoveY = animateDpAsState(
        targetValue = if (laughing) 4.dp else -4.dp,

        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration / 2,
                easing = EaseInOutCirc
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    AnimatedVisibility(
        visible = laughing,
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

                Box(
                    modifier = Modifier.wrapContentSize()
                ) {

                    Image(
                        painter = painterResource("icon.png"),
                        contentDescription = "background",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(200.dp).offset(0.dp, laughMoveY.value)
                    )
                }

                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource("logo.png"),
                        contentDescription = "background",

                        modifier = Modifier.size(width = 400.dp, height = 100.dp)

                    )

                }


                Button(
                    onClick = {
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
    LaunchedEffect(Unit) {
        delay(1)
        laughing = true
    }
}
