package menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SettingsMenu(

    HandleBack: (String) -> Unit
) {

    var ShouldShow by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = ShouldShow,
        label = "Settings",
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
                Text("SHIT SETTINGS")
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