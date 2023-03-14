package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.auth.data.remote.model.LoginRequest
import com.ibm.bni.auth.data.remote.model.LoginResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import com.ibm.bni.auth.domain.usecase.LoginUseCase
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginUserIdPasswordViewModel"
class LoginUserIdPasswordViewModel @Inject constructor(
    private val repository: PreLoginRepository,
    private val loginUseCase: LoginUseCase
):BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun clearErrorMessage(){
        _errorMessage.postValue(null)
    }

    fun login(data: LoginRequest){
        coroutineScope.launch {
            repository.login(data).onStart {
                Log.e(TAG, "onStart----------------------------")
                _isLoading.postValue(true)
            }.collect { result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------")
                    if (it != null) {
                        _loginResult.postValue(it)
                    }
                    _isLoading.postValue(false)
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    _isLoading.postValue(false)
                    _errorMessage.postValue(it.localizedMessage)
                }
            }
        }
    }

}