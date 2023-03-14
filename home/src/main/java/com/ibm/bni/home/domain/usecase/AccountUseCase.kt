package com.ibm.bni.home.domain.usecase

import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.ListTransactionModel
import com.ibm.bni.home.domain.repository.AccountRepository
import com.ibm.bni.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

fun interface AccountUseCase:()-> Flow<Result<ListTransactionModel?>>

fun listTransaction(accountRepository: AccountRepository):Flow<Result<ListTransactionModel?>> = accountRepository.listTransaction("000001")