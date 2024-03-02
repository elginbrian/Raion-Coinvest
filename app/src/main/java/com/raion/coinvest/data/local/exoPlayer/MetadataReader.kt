package com.raion.coinvest.data.local.exoPlayer

import android.net.Uri

interface MetadataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetadataDataClass
}