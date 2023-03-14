package com.ibm.bni.home.domain.usecase

import android.graphics.Point
import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.PointModel
import com.ibm.bni.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

fun interface HomeUseCase:()-> Flow<Result<ListAccountModel?>>

fun listAccount(homeRepository: HomeRepository):Flow<Result<ListAccountModel?>> = homeRepository.listAccount("CIF-00001")
fun getPoint(homeRepository: HomeRepository):Flow<Result<PointModel?>> = homeRepository.getPoint("CIF-00001")