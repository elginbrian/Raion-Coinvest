package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class PostDataClass(
    val postId: String,
    val communityId: String,
    val postAuthor: UserDataClass,
    val postCreatedAt: String,
    val postContent: String,
    var imageUri: Uri,
)