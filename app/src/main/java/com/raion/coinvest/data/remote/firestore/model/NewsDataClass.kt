package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class NewsDataClass(
    val newsId: String,
    val newsTitle: String,
    val newsCategory: String,
    val newsAuthor: UserDataClass,
    val newsCreatedAt: String,
    val newsContent: String,
    var imageUri: Uri
)