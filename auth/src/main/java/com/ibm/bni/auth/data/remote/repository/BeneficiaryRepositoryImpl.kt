package com.ibm.bni.auth.data.remote.repository

import android.util.Log
import com.ibm.bni.auth.data.remote.api.BeneficiaryApi
import com.ibm.bni.auth.data.remote.model.*
import com.ibm.bni.auth.domain.repository.BeneficiaryRepository
import com.ibm.bni.auth.presentation.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BeneficiaryRepositoryImpl @Inject constructor(private val beneficiaryApi: BeneficiaryApi) : BeneficiaryRepository {

    override fun getReceivers(cif: String): Flow<Result<ReceiverResponse?>> = flow {
        try {
            Log.e("cif", "" + cif)
            val response = beneficiaryApi.getReceivers(Constant.GET_RECEIVERS)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (exception: Exception) {
            emit(Result.failure(exception.fillInStackTrace()))
        }

    }
}