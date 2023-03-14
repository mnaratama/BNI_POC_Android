package com.ibm.bni.auth.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.data.remote.model.ValidateCredentialResult
import com.ibm.bni.auth.data.remote.model.ValidateOtpResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

@Keep
interface AuthApi {

    @GET
    suspend fun generateOtp(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<CreateOtpResult>

    @GET
    suspend fun validateOTP(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<ValidateOtpResult>

    @POST
    suspend fun validateCredential(@Url url : String?, @Body credentialRequest: CredentialRequest) : Response<ValidateCredentialResult>
}