package com.ibm.bni.auth.domain.usecase

    import com.ibm.bni.auth.data.remote.model.ReceiverResponse
import com.ibm.bni.auth.domain.repository.BeneficiaryRepository
import kotlinx.coroutines.flow.Flow

fun interface GetReceiverUseCase : () -> Flow<Result<ReceiverResponse?>>

fun getReceivers(beneficiaryRepository: BeneficiaryRepository) : Flow<Result<ReceiverResponse?>> = beneficiaryRepository.getReceivers(cif = "")
