package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.SeeBalanceData
import com.ibm.bni.auth.data.remote.model.SeeBalanceResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow

fun interface SeeBalanceUseCase:()-> Flow<Result<SeeBalanceData?>>

fun seeBalance(preLoginRepository: PreLoginRepository):Flow<Result<SeeBalanceData?>> = preLoginRepository.seeBalance("000")