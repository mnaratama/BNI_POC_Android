package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.bni.auth.data.remote.model.ReceiverFields
import com.ibm.bni.auth.domain.repository.BeneficiaryRepository
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "Choose Beneficiary"

class BenificayViewModel @Inject constructor(private val beneficiaryRepository: BeneficiaryRepository): BaseViewModel() {

    val isSuccess = MutableLiveData<Boolean>()
    val receiverFields = MutableLiveData<List<ReceiverFields>>()

    fun fetchReceivers(cif: String) {
        viewModelScope.launch {
            beneficiaryRepository.getReceivers(cif).onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isSuccess.value = it?.success
                    receiverFields.value = it?.Receivers
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isSuccess.value = false
                }
            }
        }
    }
}
