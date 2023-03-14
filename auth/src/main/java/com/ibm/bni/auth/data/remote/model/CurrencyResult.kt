package com.ibm.bni.auth.data.remote.model

data class CurrencyResult(val success : Boolean, val status : Int?=null, val message : String, val currency: List<CurrencyList> = arrayListOf())


data class CurrencyList(val currency : String?=null, val value : Double?=null, val countryCode : Int?=null, val baseCountry: String?=null, val currencysymbol:Any? = null, val countryName: String?=null)