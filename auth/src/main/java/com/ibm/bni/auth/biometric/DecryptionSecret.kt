package com.ibm.bni.auth.biometric

data class DecryptionSecret(
    val alias: String,
    val data: String,
    val iv: String
)