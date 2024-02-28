package com.raion.coinvest.model.remote

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
