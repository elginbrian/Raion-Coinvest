package com.raion.coinvest.data.remote.firebaseStorage.model

import android.net.Uri

data class VerifDataClass(
    val userId: String,
    val accountType: String,
    val document1: Uri,
    val document2: Uri,
)