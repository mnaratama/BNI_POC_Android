package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.auth.domain.usecase.GenerateOtpUseCase
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AuthViewModel"

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository,
                                        private val generateOtpUseCase: GenerateOtpUseCase): BaseViewModel() {

    /*init {
        coroutineScope.launch {
            generateOtpUseCase().onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                }
            }
        }
    }*/
}