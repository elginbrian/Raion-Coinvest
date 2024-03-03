package com.raion.coinvest.data.remote.auth.model

data class SignInResult(
    var isSuccess: Boolean = false,
    var data: UserData?,
    var errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String,
    val profilePictureUrl: String
)
