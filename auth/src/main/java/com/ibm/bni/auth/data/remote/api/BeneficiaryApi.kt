package com.ibm.bni.auth.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.auth.data.remote.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

@Keep
interface BeneficiaryApi {

    @GET
    suspend fun getReceivers(@Url url : String?) : Response<ReceiverResponse>

}