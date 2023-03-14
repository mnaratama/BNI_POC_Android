package com.ibm.bni.home.data.remote.model

data class ListTransactionResult(
    var transactionType: String,
    var amount: Double,
    var createdDate: String
)