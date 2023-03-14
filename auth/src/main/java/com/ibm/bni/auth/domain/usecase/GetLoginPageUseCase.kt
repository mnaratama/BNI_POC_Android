package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow

fun interface GetLoginPageUseCase :()-> Flow<Result<String?>>


fun getLoginPagePref(authRepository: PreLoginRepository) : Flow<Result<String?>> = authRepository.getLoginPagePref()