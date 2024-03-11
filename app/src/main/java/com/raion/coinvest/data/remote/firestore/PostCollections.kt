package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostCollections @Inject constructor(
    private val imageRepository: ImageRepository
) {
    val db = Firebase.firestore
    suspend fun addPost(post: PostDataClass){
        try {
            val postHashMap = hashMapOf(
                "postId" to post.postId,
                "communityId" to post.communityId,
                "postAuthor" to post.postAuthor,
                "postCreatedAt" to post.postCreatedAt,
                "postContent" to post.postContent
            )

            db.collection("posts").document(post.postId).set(postHashMap).addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $documentReference")

            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }.await()
        } catch (_: Exception){

        }
    }

    suspend fun getPost(): MutableList<PostDataClass>{
        val postList: MutableList<PostDataClass> = mutableListOf()
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                    val postAuthorData = document.get("postAuthor") as? Map<*, *>
                    val postAuthor  = UserDataClass(
                        userId         = postAuthorData?.get("userId").toString(),
                        userName       = postAuthorData?.get("userName").toString(),
                        profilePicture = postAuthorData?.get("profilePicture").toString(),
                        accountType    = postAuthorData?.get("accountType").toString(),
                        email          = postAuthorData?.get("email").toString()
                    )

                    postList.add(element = PostDataClass(
                        postId        = document["postId"].toString(),
                        communityId   = document["communityId"].toString(),
                        postContent   = document["postContent"].toString(),
                        postAuthor    = postAuthor,
                        postCreatedAt = document["postCreatedAt"].toString(),
                        imageUri         = Uri.EMPTY
                    )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }.await()
        return postList
    }
}