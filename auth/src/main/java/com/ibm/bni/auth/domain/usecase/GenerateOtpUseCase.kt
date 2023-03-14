package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.data.remote.model.ValidateCredentialResult
import com.ibm.bni.auth.data.remote.model.ValidateOtpResult
import com.ibm.bni.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

fun interface GenerateOtpUseCase : () -> Flow<Result<CreateOtpResult?>>

fun generateOtp(authRepository: AuthRepository) : Flow<Result<CreateOtpResult?>> = authRepository.generateOtp(mobileNo = "")

fun validateOtp(authRepository: AuthRepository) : Flow<Result<ValidateOtpResult?>> = authRepository.validateOtp(otp = "")

fun validateCredential(authRepository: AuthRepository) : Flow<Result<ValidateCredentialResult?>> = authRepository.validateCredential(credentialRequest = CredentialRequest("",""))
