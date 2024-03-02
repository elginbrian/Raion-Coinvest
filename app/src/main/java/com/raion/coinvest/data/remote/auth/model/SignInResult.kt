package com.raion.coinvest.data.remote.auth.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String,
    val profilePictureUrl: String
)
