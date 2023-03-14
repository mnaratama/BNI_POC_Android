package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.NewAndHotDealResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import kotlinx.coroutines.flow.Flow

fun interface GetNewAndHotDealUseCase:()-> Flow<Result<NewAndHotDealResult?>>

fun getNewAndHotDeal(preLoginRepository: PreLoginRepository):Flow<Result<NewAndHotDealResult?>> = preLoginRepository.getNewAndHotDeals()