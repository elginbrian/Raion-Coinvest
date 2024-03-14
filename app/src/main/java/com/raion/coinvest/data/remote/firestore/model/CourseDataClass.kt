package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class CourseDataClass(
    val courseId: String,
    val courseName: String,
    val courseDescription: String,
    val courseCategory: String,
    val coursePrice: String,
    val courseOwner: UserDataClass,
    val courseContent: MutableList<CourseContent>
)

data class CourseContent(
    val videoId: String,
    val videoTitle: String,
    val videoDescription: String,
    val videoCopyright: String,
    var videoUri: Uri
)
