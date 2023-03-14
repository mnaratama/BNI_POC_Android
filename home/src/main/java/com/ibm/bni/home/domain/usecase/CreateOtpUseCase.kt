package com.ibm.bni.home.domain.usecase

import com.ibm.bni.home.data.remote.model.CreateOtpResult
import com.ibm.bni.home.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

fun interface CreateOtpUseCase : () -> Flow<Result<CreateOtpResult?>>

fun createOtp(authRepository: AuthRepository) : Flow<Result<CreateOtpResult?>> = authRepository.createOtp()
