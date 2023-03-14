package com.ibm.bni.home.data.remote.model

data class ListAccountResult(
    var accountNo: String,
    var currentBalance: Double,
    var productType: String
)