package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class ArticleDataClass(
    val articleId: String,
    val articleTitle: String,
    val articleAuthor: UserDataClass,
    val articleCreatedAt: String,
    val articleContent: String,
    var imageUri: Uri,
)