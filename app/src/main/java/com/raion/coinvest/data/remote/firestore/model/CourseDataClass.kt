package com.raion.coinvest.data.remote.firestore.model

data class CourseDataClass(
    val courseId: String,
    val courseName: String,
    val courseOwner: UserDataClass
)
