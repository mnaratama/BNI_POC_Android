package com.ibm.bni.auth.data.remote.model

data class Transaction(
    val amount: Double,
    val createdDate: String,
    val transactionType: String
)