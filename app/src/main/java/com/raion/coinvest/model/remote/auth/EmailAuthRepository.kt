package com.raion.coinvest.model.remote.auth

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class EmailAuthRepository {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.uid.orEmpty()

    suspend fun createUser(email: String, password: String): SignInResult{
        var signInResult = SignInResult(null, null)
        try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    Log.d(TAG, "signInWithEmail:success")
                    val user = Firebase.auth.currentUser
                    signInResult = SignInResult(
                        data = user?.run {
                            UserData(
                                userId = uid,
                                username = displayName.orEmpty(),
                                profilePictureUrl = photoUrl?.toString().orEmpty()
                            )
                        },
                        errorMessage = null
                    )

                } else {
                    Log.w(TAG, "signInWithEmail:failure", it.exception)
                }
            }.await()

        } catch (e: Exception){
            Log.d("Exception", e.message.toString())
            signInResult = SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
        return signInResult
    }

    suspend fun loginUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                Log.d(TAG, "signInWithEmail:success")
            } else {
                Log.w(TAG, "signInWithEmail:failure", it.exception)
            }
        }.await()
    }
}