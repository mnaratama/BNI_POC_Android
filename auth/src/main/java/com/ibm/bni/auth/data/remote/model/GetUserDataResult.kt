package com.ibm.bni.auth.data.remote.model

data class GetUserDataResult(
    val data: String,
    val error: Error,
    val message: String,
    val responsestatuscode: Int,
    val userData: UserData
)
data class UserData(
    val accountno: String,
    val acid: String,
    val bindidmobilenumber: String,
    val cif: String,
    val mobilenumber: String,
    val accountname:String,
    val password: String
)