package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "Credential Input"

class CustomerRegistrationEnterCredentialViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {

    val isSuccess = MutableLiveData<Boolean>()
    fun validateCredential(credentialRequest: CredentialRequest) {
        viewModelScope.launch {
            authRepository.validateCredential(credentialRequest).onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isSuccess.value = true
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isSuccess.value = false
                }
            }
        }
    }
}