package com.raion.coinvest.data.remote.firebaseStorage

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VideoRepository {
    var storageRef = Firebase.storage.reference

    suspend fun uploadVideo(course: CourseDataClass){
        val metadata = storageMetadata {
            contentType = "video/mp4"
        }

        val uploadTask = storageRef.child("videos/${course.courseId}/${course.courseContent[0].videoId}").putFile(course.courseContent[0].videoUri, metadata)

        uploadTask.addOnProgressListener {
            val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
            Log.d(ContentValues.TAG, "Upload is $progress% done")
        }.addOnPausedListener {
            Log.d(ContentValues.TAG, "Upload is paused")
        }.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener {
            // Handle successful uploads on complete
            // ...
        }.await()
    }

    suspend fun getVideo(courseId: String, videoId: String): Uri = suspendCoroutine { continuation ->
        try {
            val pathReference = storageRef.child("videos/${courseId}/${videoId}")
            Log.d("getVideo", "getVideo called")
            Log.d("getVideo", pathReference.toString())
            pathReference.downloadUrl.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("getVideo", "getVideo Success")
                    continuation.resume(task.result)
                } else {
                    Log.d("getVideo", "getVideo Failed: ${task.exception?.localizedMessage}")
                    continuation.resume(Uri.EMPTY)
                }
            }
        } catch (e: Exception) {
            Log.d("getVideo", "getVideo Failed: ${e.localizedMessage}")
            continuation.resume(Uri.EMPTY)
        }
    }
}