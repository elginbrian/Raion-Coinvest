package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class CommentDataClass(
    val commentId: String,
    val parentId: String,
    val commentContent: String,
    var imageUri: Uri,
    val commentCreatedAt: String,
    val commentAuthor: UserDataClass
)
