package com.ibm.bni.auth.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.auth.data.remote.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url
@Keep
interface PreLoginApi {
    @POST
    suspend fun login(@Url url : String?, @Body data: LoginRequest) : Response<LoginResult>

    @GET
    suspend fun seeBalance(@Url url : String?, @QueryMap headers : MutableMap<String, String>) : Response<SeeBalanceData>

    @GET
    suspend fun getNewAndHotDeals(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<NewAndHotDealResult>

    @GET
    suspend fun getRegisteredUser(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<RegisteredUserResponse>

    @GET
    suspend fun getLoginPref(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) : Response<String?>

    @GET
    suspend fun getUserData(@Url url : String?, @HeaderMap headers : MutableMap<String, String>) :Response<GetUserDataResult>
}