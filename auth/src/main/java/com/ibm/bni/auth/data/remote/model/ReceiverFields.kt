package com.ibm.bni.auth.data.remote.model


data class ReceiverFields (
    var id                      : Int,
    var payerAcNumber           : String,
    var payerName               : String,
    var payerBaseCurrency       : String,
    var payerBaseCurrencySymbol : String,
    var receiverPaymentMode     : String,
    var receiverCountryName     : String,
    var receiverBankName        : String,
    var receiverAcNumber        : String,
    var receiverCityName        : String,
    var receiverLocationName    : String,
    var receiverCurrency        : String,
    var receiverCurrencySymbol  : String,
    var receiverName            : String,
    var receiverAddressLine     : String,
    var receiverType            : String,
    var receiverAccountType     : String,
    var swiftCode               : String,
    var transferId              : String,
    var agreement               : String,
    var createdDate             : String,
    var createdBy               : String

)