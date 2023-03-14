package com.ibm.bni.auth.data.remote.model

data class SeeBalanceData(
    val currentBalance: Double,
    val productType: String,
    val transactions: List<Transaction>
)

