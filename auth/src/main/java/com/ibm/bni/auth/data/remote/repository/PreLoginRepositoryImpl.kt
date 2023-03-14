package com.ibm.bni.auth.data.remote.repository

import com.ibm.bni.auth.data.remote.api.PreLoginApi
import com.ibm.bni.auth.data.remote.model.*
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreLoginRepositoryImpl @Inject constructor(private val preLoginApi: PreLoginApi) :PreLoginRepository {
    override fun login(data:LoginRequest): Flow<Result<LoginResult?>>  = flow {
        try {
            val headers = mutableMapOf(Pair("", ""))
            val response = preLoginApi.login("https://productservice-mavipoc-ps.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/verifyCredentials", data)
            if (response.isSuccessful) {
                if(response.body()!=null){
                    if(response.body()!!.responsestatuscode==200){
                        emit(Result.success(response.body()))
                    }else{
                        emit(Result.failure(Throwable(response.message())))
                    }
                }else{
                    emit(Result.failure(Throwable(response.message())))
                }
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun seeBalance(accountNo:String):Flow<Result<SeeBalanceData?>> = flow {
        try {
            val headers = mutableMapOf(Pair("accountNo", accountNo))
            val response = preLoginApi.seeBalance("https://accountservice-mavipoc-accountservice.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/account/balance", headers)
            if (response.isSuccessful) {
                if(response.body()!=null){
                    if(response.code()==200){
                        emit(Result.success(response.body()))
                    }else{
                        emit(Result.failure(Throwable(response.message())))
                    }
                }else{
                    emit(Result.failure(Throwable(response.message())))
                }
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun getNewAndHotDeals(): Flow<Result<NewAndHotDealResult?>> = flow {
        try {
            emit(Result.success(NewAndHotDealResult("ad",1,"")))
            /*val headers = mutableMapOf(Pair("", ""))
            val response = preLoginApi.getNewAndHotDeals("", headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }*/
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun getRegisteredUser(): Flow<Result<RegisteredUserResponse?>> = flow {
        try {
//            emit(Result.success(NewAndHotDealResult("ad")))
            val headers = mutableMapOf(Pair("", ""))
            val response = preLoginApi.getRegisteredUser("", headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun getLoginPagePref(): Flow<Result<String?>> =flow {
        try {
            val headers = mutableMapOf(Pair("", ""))
            val response = preLoginApi.getLoginPref("", headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }

    override fun getUserData(userId:String): Flow<Result<GetUserDataResult?>> = flow {
        try {
            val headers = mutableMapOf(Pair("userId", userId))
            val response = preLoginApi.getUserData("https://productservice-mavipoc-ps.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/getUserData", headers)
            if (response.isSuccessful) {
                if(response.body()!=null){
                    if(response.body()!!.responsestatuscode==200){
                        emit(Result.success(response.body()))
                    }else{
                        emit(Result.failure(Throwable(response.message())))
                    }
                }else{
                    emit(Result.failure(Throwable(response.message())))
                }
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }
    }


}