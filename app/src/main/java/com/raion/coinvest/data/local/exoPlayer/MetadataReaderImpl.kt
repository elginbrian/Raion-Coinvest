package com.raion.coinvest.data.local.exoPlayer

import android.app.Application
import android.net.Uri
import android.provider.MediaStore

class MetadataReaderImpl(
    private val app: Application
): MetadataReader {
    override fun getMetaDataFromUri(contentUri: Uri): MetadataDataClass {
        if(contentUri.scheme != "content"){
            return MetadataDataClass(null)
        }
        val fileName = app.contentResolver.query(
            contentUri,
            arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
            null, null, null

        )?.use {cursor ->
            val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(index)
        }
        return fileName.let { fullName ->
            MetadataDataClass(
                fileName = Uri.parse(fullName).lastPathSegment ?: return MetadataDataClass(null)
            )
        }
    }
}