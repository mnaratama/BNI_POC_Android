package com.ibm.bni.auth.data.remote.repository

import com.ibm.bni.auth.data.remote.api.TransactionApi
import com.ibm.bni.auth.data.remote.model.CurrencyResult
import com.ibm.bni.auth.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionApi : TransactionApi) : TransactionRepository {

    override fun getCurrency(): Flow<Result<CurrencyResult?>> = flow {
        try {
            val headers = mutableMapOf(Pair("header", "[]"))
            val response = transactionApi.getCurrency("https://paymentservice-mavipoc-payment-service.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/master/currency")
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