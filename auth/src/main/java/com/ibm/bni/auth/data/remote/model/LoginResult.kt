package com.ibm.bni.auth.data.remote.model

data class LoginResult(
    val data: String,
    val error: Error,
    val message: String,
    val responsestatuscode: Int,
    val userData: String
    )