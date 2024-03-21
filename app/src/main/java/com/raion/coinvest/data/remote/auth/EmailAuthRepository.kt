package com.raion.coinvest.data.remote.auth

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.model.UserData
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.model.VerifDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class EmailAuthRepository @Inject constructor(
    private val imageRepository: ImageRepository
) {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser
    suspend fun loginViaEmail(email: String, password: String): SignInResult = suspendCoroutine { continuation ->
        var signInResult = SignInResult(isSuccess = false, data = null, errorMessage = null)
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
                    Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
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
                            }
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", it.exception)
                            signInResult = SignInResult(
                                isSuccess = false,
                                data = null,
                                errorMessage = task.exception?.message
                            )
                        }
                    }
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

    suspend fun updateAuthData(userDataClass: UserDataClass){
        val user = Firebase.auth.currentUser
        val newPhotoUrl = imageRepository.getUserProfile(Firebase.auth.currentUser?.uid.toString())

        val profileUpdates = userProfileChangeRequest {
            displayName = userDataClass.userName
            photoUri =  if(newPhotoUrl != Uri.EMPTY){
                newPhotoUrl
            } else {
                Uri.parse(userDataClass.profilePicture)
            }
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }.await()
    }
}