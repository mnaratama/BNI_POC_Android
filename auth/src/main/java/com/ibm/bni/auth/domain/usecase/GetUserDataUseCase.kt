package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.GetUserDataResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow


fun interface GetUserDataUseCase : () -> Flow<Result<GetUserDataResult?>>

fun getUserData(preLoginRepo: PreLoginRepository): Flow<Result<GetUserDataResult?>> = preLoginRepo.getUserData("00")