package com.raion.coinvest.data.remote.firebaseStorage

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import com.raion.coinvest.data.remote.firebaseStorage.model.VerifDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ImageRepository {
    var storageRef = Firebase.storage.reference

    suspend fun uploadPostImage(post: PostDataClass) {
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        if(post.imageUri != Uri.EMPTY){
            val uploadTask = storageRef.child("images/${post.postId}").putFile(post.imageUri, metadata)

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

    suspend fun uploadVerifImage(verif: VerifDataClass){
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        if(verif.document1 != Uri.EMPTY){
            val uploadTask = storageRef.child("verif/${verif.accountType}/${verif.userId}/${verif.document1}").putFile(verif.document1, metadata)
            uploadTask.addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                Log.d(TAG, "Upload is $progress% done")
            }.addOnPausedListener {
                Log.d(TAG, "Upload is paused")
            }.addOnFailureListener {

            }.addOnSuccessListener {

            }.await()
        }

        if(verif.document2 != Uri.EMPTY){
            val uploadTask = storageRef.child("verif/${verif.accountType}/${verif.userId}/${verif.document2}").putFile(verif.document2, metadata)
            uploadTask.addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                Log.d(TAG, "Upload is $progress% done")
            }.addOnPausedListener {
                Log.d(TAG, "Upload is paused")
            }.addOnFailureListener {

            }.addOnSuccessListener {

            }.await()
        }
    }


    suspend fun uploadNewsImage(news: NewsDataClass) {
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        if(news.imageUri != Uri.EMPTY){
            val uploadTask = storageRef.child("images/${news.newsId}").putFile(news.imageUri, metadata)

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

    suspend fun uploadCommentImage(comment: CommentDataClass) {
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        if(comment.imageUri != Uri.EMPTY){
            val uploadTask = storageRef.child("images/${comment.commentId}").putFile(comment.imageUri, metadata)

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

    suspend fun getImage(articleId: String): Uri = suspendCoroutine { continuation ->
        val pathReference = storageRef.child("images/$articleId")
        pathReference.downloadUrl.addOnSuccessListener {
            Log.d(TAG, "getImage Success")
            continuation.resume(it)
        }.addOnFailureListener {
            // Handle failure, you might want to provide a default image or log an error
            Log.d(TAG, "getImage Failed: ${it.localizedMessage}")
            continuation.resume(Uri.EMPTY)
        }
    }

    suspend fun uploadProfilePict(userDataClass: UserDataClass){
        val metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        if(Uri.parse(userDataClass.profilePicture) != Uri.EMPTY){
            val uploadTask = storageRef.child("profile_picture/${Firebase.auth.currentUser?.uid.toString()}/${Firebase.auth.currentUser?.uid.toString()}").putFile(Uri.parse(userDataClass.profilePicture), metadata)

            uploadTask.addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                Log.d(TAG, "Upload is $progress% done")
            }.addOnPausedListener {
                Log.d(TAG, "Upload is paused")
            }.addOnFailureListener {

            }.addOnSuccessListener {

            }.await()
        }
    }

    suspend fun getUserProfile(userId: String): Uri = suspendCoroutine { continuation ->
        val pathReference = storageRef.child("profile_picture/$userId/$userId")
        pathReference.downloadUrl.addOnSuccessListener {
            Log.d(TAG, "getUserProfile Success")
            continuation.resume(it)
        }.addOnFailureListener {
            // Handle failure, you might want to provide a default image or log an error
            Log.d(TAG, "getUserProfile Failed: ${it.localizedMessage}")
            continuation.resume(Uri.EMPTY)
        }
    }
}
