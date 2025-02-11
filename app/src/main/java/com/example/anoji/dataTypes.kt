package com.example.anoji

data class SignInResult(
    val data: com.example.anoji.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String,
    val ppurl: String?,
    val email : String?
)