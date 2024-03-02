package com.raion.coinvest.data.local.videoplayer

import android.net.Uri

interface MetadataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetadataDataClass
}