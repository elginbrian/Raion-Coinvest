package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class CourseCollections {
    val db = Firebase.firestore

    suspend fun addCourse(course: CourseDataClass){
        try {
            val contentHashMap: MutableList<HashMap<*,*>> = mutableListOf()
            for(content in course.courseContent){
                contentHashMap.add(hashMapOf(
                    "videoId" to content.videoId,
                    "videoTitle" to content.videoTitle,
                    "videoDescription" to content.videoDescription,
                    "videoCopyright" to content.videoCopyright
                ))
            }

            val courseHashMap = hashMapOf(
                "courseId" to course.courseId,
                "courseName" to course.courseName,
                "courseDescription" to course.courseDescription,
                "courseCategory" to course.courseCategory,
                "coursePrice" to course.coursePrice,
                "courseOwner" to course.courseOwner,
                "courseContent" to contentHashMap
            )

            db.collection("courses").document(course.courseId).set(courseHashMap).addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $documentReference")

            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }.await()
        } catch (_: Exception){

        }
    }

    suspend fun getCourse(): MutableList<CourseDataClass> {
        val courseList: MutableList<CourseDataClass> = mutableListOf()
        try {
            val result = db.collection("courses").get().await()
            for (document in result.documents) {
                val courseOwnerData = document.get("courseOwner") as? Map<*, *>
                val courseOwner = UserDataClass(
                    userId = courseOwnerData?.get("userId").toString(),
                    userName = courseOwnerData?.get("userName").toString(),
                    profilePicture = courseOwnerData?.get("profilePicture").toString(),
                    accountType = courseOwnerData?.get("accountType").toString(),
                    email = courseOwnerData?.get("email").toString()
                )

                val courseContentList = mutableListOf<CourseContent>()
                val courseContentData = document.get("courseContent") as? List<Map<*, *>>
                courseContentData?.forEach { contentMap ->
                    val videoTitle = contentMap["videoTitle"] as? String ?: ""
                    val videoId = contentMap["videoId"] as? String ?: ""
                    val videoDescription = contentMap["videoDescription"] as? String ?: ""
                    val videoCopyright = contentMap["videoCopyright"] as? String ?: ""
                    val courseContent = CourseContent(
                        videoTitle = videoTitle,
                        videoId = videoId,
                        videoDescription = videoDescription,
                        videoCopyright = videoCopyright,
                        videoUri = Uri.EMPTY
                    )
                    courseContentList.add(courseContent)
                }

                val course = CourseDataClass(
                    courseId = document["courseId"].toString(),
                    courseName = document["courseName"].toString(),
                    courseDescription = document["courseDescription"].toString(),
                    courseCategory = document["courseCategory"].toString(),
                    coursePrice = document["coursePrice"].toString(),
                    courseOwner = courseOwner,
                    courseContent = courseContentList
                )
                courseList.add(course)
            }
        } catch (e: Exception) {
            Log.d("getCourse", "Error getting courses: ${e.localizedMessage}")
        }
        return courseList
    }
}