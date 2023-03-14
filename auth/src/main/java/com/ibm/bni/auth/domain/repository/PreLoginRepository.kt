package com.ibm.bni.auth.domain.repository

import com.ibm.bni.auth.data.remote.model.*
import kotlinx.coroutines.flow.Flow

interface PreLoginRepository {
    fun login(data:LoginRequest): Flow<Result<LoginResult?>>
    fun seeBalance(accountNo:String):Flow<Result<SeeBalanceData?>>
    fun getNewAndHotDeals() : Flow<Result<NewAndHotDealResult?>>
    fun getRegisteredUser() : Flow<Result<RegisteredUserResponse?>>
    fun getLoginPagePref() : Flow<Result<String?>>
    fun getUserData(userId:String) :Flow<Result<GetUserDataResult?>>
}