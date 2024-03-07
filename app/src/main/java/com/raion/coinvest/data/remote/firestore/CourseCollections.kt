package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class CourseCollections {
    val db = Firebase.firestore

    suspend fun addCourse(course: CourseDataClass){
        try {

            val courseHashMap = hashMapOf(
                "courseId" to course.courseId,
                "courseName" to course.courseName,
                "courseOwner" to course.courseOwner,
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
        db.collection("courses").get().addOnCompleteListener{ result ->
            for(document in result.result){
                Log.d("getCourse", "${document.id} => ${document.data}")
                val courseOwnerData = document.get("courseOwner") as? Map<*,*>
                val courseOwner     = UserDataClass(
                    userId          = courseOwnerData?.get("userId").toString(),
                    userName        = courseOwnerData?.get("userName").toString(),
                    profilePicture  = courseOwnerData?.get("profilePicture").toString(),
                    accountType     = courseOwnerData?.get("accountType").toString(),
                    email           = courseOwnerData?.get("email").toString()
                )

                courseList.add(element = CourseDataClass(
                    courseId    = document["courseId"].toString(),
                    courseName  = document["courseName"].toString(),
                    courseOwner = courseOwner,
                    courseContent = mutableListOf()
                )
                )
            }
        }.addOnFailureListener{
            it.localizedMessage?.let { it1 -> Log.d("getCourse", it1) }
        }
        return courseList
    }
}