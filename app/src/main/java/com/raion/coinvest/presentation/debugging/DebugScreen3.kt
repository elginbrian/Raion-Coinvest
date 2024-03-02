package com.raion.coinvest.presentation.debugging

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.raion.coinvest.data.local.videoplayer.MetadataDataClass
/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/
@Composable
fun DebugScreen3(
    viewModel: DebugViewModel
){
    var selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    var selectedVideoUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )
    val singleVideoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(viewModel::addVideoUri)
            selectedVideoUri.value = uri
        }
    )
    val vidioItems = viewModel.videoItems.collectAsState()
    var lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner){
        val observer = object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                lifecycle.value = event
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()){
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Pick 1 Photo")
                }
                Button(onClick = {
                    singleVideoPickerLauncher.launch("video/mp4")
                }) {
                    Text(text = "Pick 1 Video")
                }
            }
        }
        item {
            Button(onClick = {
                selectedImageUri.value?.let { viewModel.addImageToFireStore(it) }
            }) {
                Text(text = "Upload Image to Firebase")
            }

            Button(onClick = {
                selectedVideoUri.value?.let { viewModel.addVideoToFireStore(it) }
            }) {
                Text(text = "Upload Video to Firebase")
            }
        }

        item {
            AsyncImage(
                model = selectedImageUri.value, contentDescription = "Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        item {
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

        items(vidioItems.value){
            Text(text = it.name, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.playVideo(it.contentUri)
                })
        }
    }
}