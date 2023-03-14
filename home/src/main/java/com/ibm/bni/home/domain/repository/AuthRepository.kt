package com.ibm.bni.home.domain.repository

import com.ibm.bni.home.data.remote.model.CreateOtpResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createOtp() : Flow<Result<CreateOtpResult?>>
}