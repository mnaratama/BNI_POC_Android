package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.RegisteredUserResponse
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow

fun interface GetRegisteredUserUseCase : () -> Flow<Result<RegisteredUserResponse?>>

fun getRegisteredUser(authRepository: PreLoginRepository) : Flow<Result<RegisteredUserResponse?>> = authRepository.getRegisteredUser()