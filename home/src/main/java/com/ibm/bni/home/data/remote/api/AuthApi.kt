package com.ibm.bni.home.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.home.data.remote.model.CreateOtpResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

@Keep
interface AuthApi {

    @GET
    suspend fun createOtp(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<CreateOtpResult>
}