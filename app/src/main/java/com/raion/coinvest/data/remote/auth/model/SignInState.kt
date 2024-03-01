package com.raion.coinvest.data.remote.auth.model

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
