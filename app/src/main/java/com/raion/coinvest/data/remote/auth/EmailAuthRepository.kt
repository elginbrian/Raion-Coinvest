package com.raion.coinvest.data.remote.auth

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.model.UserData
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class EmailAuthRepository {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.uid.orEmpty()

    suspend fun createUser(email: String, password: String): SignInResult = suspendCoroutine { continuation ->
        var signInResult: SignInResult
        try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = Firebase.auth.currentUser
                    signInResult = SignInResult(
                        isSuccess = true,
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
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    signInResult = SignInResult(
                        isSuccess = false,
                        data = null,
                        errorMessage = task.exception?.message
                    )
                }
                continuation.resume(signInResult)
            }
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
            signInResult = SignInResult(
                isSuccess = false,
                data = null,
                errorMessage = e.message
            )
            continuation.resume(signInResult)
        }
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