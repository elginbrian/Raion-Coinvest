package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import kotlinx.coroutines.tasks.await

class LikeCollections {
    val db = Firebase.firestore
    suspend fun addLike(likeDataClass: LikeDataClass){
        try {
            val likeHashMap = hashMapOf(
                "parentId" to likeDataClass.parentId,
                "userId" to likeDataClass.userId
            )

            if(!likeDataClass.userId.isNullOrEmpty()){
                db.collection("likes").document(likeDataClass.parentId+"-"+likeDataClass.userId)
                    .set(likeHashMap).addOnSuccessListener { documentReference ->
                        Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $documentReference")

                    }.addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }.await()
            }
        } catch (e: Exception){

        }
    }

    suspend fun getLike(): MutableList<LikeDataClass>{
        val likeList: MutableList<LikeDataClass> = mutableListOf()
        db.collection("likes").get().addOnCompleteListener{ result ->
            for(document in result.result){
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                likeList.add( element = LikeDataClass(
                    parentId = document["parentId"].toString(),
                    userId = document["userId"].toString()
                )
                )
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }.await()
        return likeList
    }

    suspend fun deleteLike(likeDataClass: LikeDataClass){
        db.collection("likes").document("${likeDataClass.parentId}-${likeDataClass.userId}")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }.await()
    }
}