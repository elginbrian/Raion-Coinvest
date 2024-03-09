package com.raion.coinvest.presentation.videoPlayerCard

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.ui.PlayerView
import com.raion.coinvest.presentation.debugging.DebugViewModel

@Composable
fun VideoPlayerCard(
    viewModel: DebugViewModel
){
    val lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner){
        val observer =
            LifecycleEventObserver { _, event -> lifecycle.value = event }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Card(shape = RoundedCornerShape(16.dp)) {
        AndroidView(factory = {context ->
            PlayerView(context).also {
                it.player = viewModel.player
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
            update = {
                when(lifecycle.value){
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }
                    else -> Unit
                }
            }
        )
    }
}