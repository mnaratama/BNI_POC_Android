package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "OTP Input View Model"

class CustomerRegistrationEnterOtpViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {

    val isSuccess = MutableLiveData<Boolean>()

    val isOTPSendSuccess = MutableLiveData<Boolean>()


    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun clearErrorMessage(){
        _errorMessage.postValue(null)
    }

    fun validateOTP(otp: String) {
        viewModelScope.launch {
            authRepository.validateOtp(otp).onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isSuccess.value = it?.success
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isSuccess.value = false
                }
            }
        }
    }

    fun generateOTP(mobileNo: String) {
        viewModelScope.launch {
            authRepository.generateOtp(mobileNo).onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isOTPSendSuccess.value = it?.success
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isOTPSendSuccess.value = false
                    _errorMessage.postValue(it.localizedMessage)
                }
            }
        }
    }
}