package com.ibm.bni.auth.data.remote.repository

import android.util.Log
import com.ibm.bni.auth.data.remote.api.AuthApi
import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.data.remote.model.ValidateCredentialResult
import com.ibm.bni.auth.data.remote.model.ValidateOtpResult
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.auth.presentation.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi : AuthApi) : AuthRepository {

    override fun generateOtp(mobileNo: String): Flow<Result<CreateOtpResult?>> = flow {
        try {
            Log.e("mobile no",""+mobileNo)
            val headers = mutableMapOf(Pair("mobileNumber", mobileNo))
            val response = authApi.generateOtp(Constant.GENERATE_OTP, headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }

    }

    override fun validateOtp(otp: String): Flow<Result<ValidateOtpResult?>> = flow {
        try {
            Log.e("otp",""+otp)
            val headers = mutableMapOf(Pair("otp", otp))
            val response = authApi.validateOTP(Constant.VALIDATE_OTP, headers)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception : Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }

    }

    override fun validateCredential(credentialRequest: CredentialRequest): Flow<Result<ValidateCredentialResult?>> = flow {
        try {
            val response = authApi.validateCredential(Constant.VALIDATE_CREDENTIALS, credentialRequest)
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