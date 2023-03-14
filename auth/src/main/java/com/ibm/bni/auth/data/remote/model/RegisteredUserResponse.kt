package com.ibm.bni.auth.data.remote.model

data class RegisteredUserResponse(
    val error: Error,
    val loginPreference: LoginPreference,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class LoginPreference(
    val peekBalanceAccountNum: String,
    val peekBalanceAccountType: String,
    val peekBalanceEnable: Boolean,
    val userPreference: UserPreference
)

data class Mandatory(
    val QRPay: Boolean,
    val login: Boolean,
    val otherEwallet: Boolean,
    val tapCash: Boolean
)

data class Optional(
    val hotel: Boolean,
    val movie: Boolean,
    val travel: Boolean
)

data class UserPreference(
    val mandatory: Mandatory,
    val optional: Optional
)