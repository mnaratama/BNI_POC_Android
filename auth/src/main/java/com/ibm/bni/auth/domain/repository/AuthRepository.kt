package com.ibm.bni.auth.domain.repository

import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.data.remote.model.ValidateCredentialResult
import com.ibm.bni.auth.data.remote.model.ValidateOtpResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun generateOtp(mobileNo:  String) : Flow<Result<CreateOtpResult?>>

    fun validateOtp(otp:  String) : Flow<Result<ValidateOtpResult?>>

    fun validateCredential(credentialRequest: CredentialRequest) : Flow<Result<ValidateCredentialResult?>>

}