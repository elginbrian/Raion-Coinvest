package com.raion.coinvest.presentation.widget.videoPlayerCard

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.screen.mentorSection.MentorViewModel

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerCard(
    viewModel: MentorViewModel,
    videoUri: Uri
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
    LaunchedEffect(videoUri) {
        viewModel.addVideoUri(videoUri)
        //viewModel.playVideo(videoUri)
    }

    Card(shape = RectangleShape, colors = CardDefaults.cardColors(CoinvestBlack)) {
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
