package com.raion.coinvest.model.remote.firestore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserCollections {
    val db = Firebase.firestore

    suspend fun addUsersToFireStore(
        uid: String,
        username: String,
        accountType: String
    ){
        val user = hashMapOf(
            "uid" to uid,
            "username" to username,
            "accountType" to accountType
        )

        db.collection("users").add(user).addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")

        }.addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }.await()
    }
}