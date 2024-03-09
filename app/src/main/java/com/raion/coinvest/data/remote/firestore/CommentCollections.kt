package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class CommentCollections {
    val db = Firebase.firestore
    suspend fun addComment(comment: CommentDataClass){

        try {
            val commentHashMap = hashMapOf(
                "commentId" to comment.commentId,
                "parentId" to comment.parentId,
                "commentContent" to comment.commentContent,
                "commentCreatedAt" to comment.commentCreatedAt,
                "commentAuthor" to comment.commentAuthor
            )

            db.collection("comments").document(comment.commentId).set(commentHashMap).addOnCompleteListener{
                Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $it")
            }.addOnFailureListener{
                Log.d(ContentValues.TAG, "Error adding document: ${it.localizedMessage}")
            }.await()
        } catch (_: Exception) {

        }
    }

    suspend fun getComment(): MutableList<CommentDataClass>{
        val commentList: MutableList<CommentDataClass> = mutableListOf()
        db.collection("comments")
            .get()
            .addOnCompleteListener{ result ->
            for (document in result.result){
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                val commentAuthorData = document.get("commentAuthor") as? Map<*, *>
                val commentAuthor  = UserDataClass(
                    userId         = commentAuthorData?.get("userId").toString(),
                    userName       = commentAuthorData?.get("userName").toString(),
                    profilePicture = commentAuthorData?.get("profilePicture").toString(),
                    accountType    = commentAuthorData?.get("accountType").toString(),
                    email          = commentAuthorData?.get("email").toString()
                )

                commentList.add(element = CommentDataClass(
                    commentId        = document["commentId"].toString(),
                    parentId         = document["parentId"].toString(),
                    commentContent   = document["commentContent"].toString(),
                    commentAuthor    = commentAuthor,
                    commentCreatedAt = document["commentCreatedAt"].toString(),
                    imageUri         = Uri.EMPTY
                )
                )
            }
        }.addOnFailureListener{
            Log.d("Error", "Error getting documents: ${it.localizedMessage}")
        }.await()
        return commentList
    }
}