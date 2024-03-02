package com.raion.coinvest.data.remote.firebaseStorage

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import kotlinx.coroutines.tasks.await

class ImageRepository {
    var storageRef = Firebase.storage.reference

    suspend fun uploadImage(imageUri: Uri) {
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        val uploadTask = storageRef.child("images/${imageUri.lastPathSegment}").putFile(imageUri, metadata)

        uploadTask.addOnProgressListener {
            val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
            Log.d(TAG, "Upload is $progress% done")
        }.addOnPausedListener {
            Log.d(TAG, "Upload is paused")
        }.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener {
            // Handle successful uploads on complete
            // ...
        }.await()
    }
}
