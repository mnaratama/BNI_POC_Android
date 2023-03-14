package com.ibm.bni.auth.biometric

sealed class Secret(
    private val alias: String,
    private val data: String,
    private val iv: String
)