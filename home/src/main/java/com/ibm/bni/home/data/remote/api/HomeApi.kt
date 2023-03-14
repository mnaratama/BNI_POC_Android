package com.ibm.bni.home.data.remote.api

import androidx.annotation.Keep
import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.PointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

@Keep
interface HomeApi {
    @GET
    suspend fun listAccount(@Url url : String?, @QueryMap headers : MutableMap<String, String>) : Response<ListAccountModel>

    @GET
    suspend fun getPoint(@Url url : String?, @QueryMap headers : MutableMap<String, String>) : Response<PointModel>

}