package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.LoginRequest
import com.ibm.bni.auth.data.remote.model.LoginResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow

fun interface LoginUseCase : () -> Flow<Result<LoginResult?>>

fun login(preLoginRepo: PreLoginRepository):Flow<Result<LoginResult?>> = preLoginRepo.login(
    LoginRequest("ad","aefa")
)