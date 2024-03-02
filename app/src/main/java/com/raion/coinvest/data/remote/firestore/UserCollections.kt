package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class UserCollections {
    val db = Firebase.firestore

    suspend fun addUsersToFireStore(
        user: UserDataClass
    ){
        val userHashMap = hashMapOf(
            "userId" to user.userId,
            "userName" to user.userName,
            "email" to user.email,
            "accountType" to user.accountType,
            "profilePicture" to user.profilePicture
        )

        if(!user.userId.isNullOrEmpty()){
            db.collection("users").document(user.userId).set(userHashMap).addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: $documentReference")

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }.await()
        }
    }
}