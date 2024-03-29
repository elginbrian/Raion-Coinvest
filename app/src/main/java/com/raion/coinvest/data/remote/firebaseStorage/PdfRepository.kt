package com.raion.coinvest.data.remote.firebaseStorage

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import kotlinx.coroutines.tasks.await

class PdfRepository {
    var storageRef = Firebase.storage.reference

    suspend fun uploadPdf(pdfUri: Uri) {
        val metadata = storageMetadata {
            contentType = "application/pdf"
        }

        val uploadTask = storageRef.child("document/${pdfUri.lastPathSegment}").putFile(pdfUri, metadata)

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
}