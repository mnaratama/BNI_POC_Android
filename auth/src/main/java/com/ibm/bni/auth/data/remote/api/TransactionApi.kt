package com.ibm.bni.auth.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.auth.data.remote.model.CurrencyResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

@Keep
interface TransactionApi {

    @GET
    suspend fun getCurrency(@Url url : String) : Response<CurrencyResult>

}