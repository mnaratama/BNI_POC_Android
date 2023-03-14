package com.ibm.bni.home.data.remote.repository

import com.ibm.bni.home.domain.repository.AuthRepository
import com.ibm.bni.home.data.remote.api.AuthApi
import com.ibm.bni.home.data.remote.model.CreateOtpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi : AuthApi) : AuthRepository {

    override fun createOtp(): Flow<Result<CreateOtpResult?>> = flow {
        try {
            val headers = mutableMapOf(Pair("", ""))
            val response = authApi.createOtp("", headers)
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