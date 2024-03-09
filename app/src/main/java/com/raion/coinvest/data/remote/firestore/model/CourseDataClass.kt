package com.raion.coinvest.data.remote.firestore.model

import android.net.Uri

data class CourseDataClass(
    val courseId: String,
    val courseName: String,
    val courseOwner: UserDataClass,
    val courseContent: MutableList<CourseContent>
)

data class CourseContent(
    val videoTitle: String,
    val videoDescription: String,
    val videoUri: Uri
)
