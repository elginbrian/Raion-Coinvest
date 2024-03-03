package com.raion.coinvest.data.local.exoPlayer

import android.net.Uri
import androidx.media3.common.MediaItem

data class VideoDataClass(
    val contentUri: Uri,
    val mediaItem: MediaItem,
    val name: String
)