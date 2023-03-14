package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.bni.auth.data.remote.model.CreateOtpResult
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.auth.domain.usecase.GenerateOtpUseCase
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "OTP Generate View Model"

class CustomerRegistrationEnterMobileViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {

    val isSuccess = MutableLiveData<Boolean>()

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun clearErrorMessage(){
        _errorMessage.postValue(null)
    }

    fun generateMobileNumber(mobileNo: String) {
        viewModelScope.launch {
            authRepository.generateOtp(mobileNo).onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isSuccess.value = it?.success
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isSuccess.value = false
                    _errorMessage.postValue(it.localizedMessage)
                }
            }
        }
    }
}