package com.raion.coinvest.data.remote.auth

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.model.UserData
import kotlinx.coroutines.tasks.await

class TwitterAuthRepository() {
    private val provider = OAuthProvider.newBuilder("twitter.com")

    suspend fun createUser(context: Context): SignInResult {
        var signInResult = SignInResult(null, null)
        try {
            Firebase.auth
                .startActivityForSignInWithProvider(context as Activity, provider.build())
                .addOnSuccessListener {
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                    Log.d(ContentValues.TAG, "signInWithTwitter:success")
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
                }
                .addOnFailureListener {
                    Log.w(ContentValues.TAG, "signInWithEmail:failure")
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
}