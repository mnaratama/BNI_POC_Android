package com.ibm.bni.home.data.remote.repository

import com.ibm.bni.home.data.remote.api.HomeApi
import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.PointModel
import com.ibm.bni.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {

    override fun listAccount(accountNo:String):Flow<Result<ListAccountModel?>> = flow {
        try {
            val headers = mutableMapOf(Pair("cif", accountNo))
            val response = homeApi.listAccount("https://accountservice-mavipoc-accountservice.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/account/all", headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun getPoint(accountNo:String):Flow<Result<PointModel?>> = flow {
        try {
            val headers = mutableMapOf(Pair("", accountNo))
            val response = homeApi.getPoint("https://paymentservice-mavipoc-payment-service.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/point/$accountNo", headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }


}