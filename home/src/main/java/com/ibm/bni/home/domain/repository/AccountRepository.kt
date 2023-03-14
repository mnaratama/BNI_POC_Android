package com.ibm.bni.home.domain.repository

import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.ListTransactionModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun listTransaction(accountNo:String):Flow<Result<ListTransactionModel?>>
}