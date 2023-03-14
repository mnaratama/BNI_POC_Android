package com.ibm.bni.auth.domain.repository

import com.ibm.bni.auth.data.remote.model.*
import kotlinx.coroutines.flow.Flow

interface BeneficiaryRepository {

    fun getReceivers(cif:  String) : Flow<Result<ReceiverResponse?>>
}