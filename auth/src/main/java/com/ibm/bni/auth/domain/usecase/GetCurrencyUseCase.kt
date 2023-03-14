package com.ibm.bni.auth.domain.usecase

import com.ibm.bni.auth.data.remote.model.CurrencyResult
import com.ibm.bni.auth.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

fun interface GetCurrencyUseCase : () -> Flow<Result<CurrencyResult?>>

fun getCurrency(transactionRepository: TransactionRepository) : Flow<Result<CurrencyResult?>> = transactionRepository.getCurrency()

