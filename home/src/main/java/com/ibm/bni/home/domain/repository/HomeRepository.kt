package com.ibm.bni.home.domain.repository

import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.PointModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun listAccount(accountNo:String):Flow<Result<ListAccountModel?>>
    fun getPoint(accountNo:String):Flow<Result<PointModel?>>
}