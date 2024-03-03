package com.raion.coinvest.data.local.exoPlayer

import android.net.Uri
import com.raion.coinvest.data.local.exoPlayer.model.MetadataDataClass

interface MetadataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetadataDataClass
}