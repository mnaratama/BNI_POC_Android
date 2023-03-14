package com.ibm.bni.home.data.remote.repository

import com.ibm.bni.home.data.remote.api.AccountApi
import com.ibm.bni.home.data.remote.model.ListTransactionModel
import com.ibm.bni.home.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountApi: AccountApi) : AccountRepository {

    override fun listTransaction(accountNo:String):Flow<Result<ListTransactionModel?>> = flow {
        try {
            val headers = mutableMapOf(Pair("accountNo", accountNo))
            val response = accountApi.listTransaction("https://accountservice-mavipoc-accountservice.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/account/transaction", headers)
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