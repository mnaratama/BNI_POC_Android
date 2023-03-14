package com.ibm.bni.auth.data.remote.model

data class Error(
    val code: String,
    val message: String,
    val type: String
)