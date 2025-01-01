package menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingMenu(
    HandleExit: (String) -> Unit
) {
    var ShouldShow by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = ShouldShow,
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
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp).padding(10.dp), color = androidx.compose.ui.graphics.Color.Cyan

                )
                Text("LOADING")
                Button(onClick = {
                    HandleExit("Main")
                }) {
                    Text("Cancel")
                }
            }


        }

    }

    LaunchedEffect(Unit) {
        delay(1)
        ShouldShow = true
    }
}