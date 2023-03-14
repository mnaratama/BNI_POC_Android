package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.auth.data.remote.model.SeeBalanceData
import com.ibm.bni.auth.data.remote.model.Transaction
import com.ibm.bni.auth.data.remote.repository.PreLoginRepositoryImpl
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import com.ibm.bni.auth.domain.usecase.SeeBalanceUseCase
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PeekBalanceViewModel"

class PeekBalanceViewModel @Inject constructor(
    private val repository: PreLoginRepository,
    private val seeBalanceUseCase: SeeBalanceUseCase
) : BaseViewModel() {
    private val _peekBalanceResponse = MutableLiveData<SeeBalanceData>()
    val peekBalanceResponse: LiveData<SeeBalanceData> = _peekBalanceResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userId =MutableLiveData<String>()
    val userId:LiveData<String> = _userId

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun clearErrorMessage(){
        _errorMessage.postValue(null)
    }

    fun setUserId(newData:String){
        _userId.postValue(newData)
    }
    fun peekBalance(accountNo:String) {
        coroutineScope.launch {
            repository.seeBalance(accountNo).onStart {
                Log.e(TAG, "Onstart----------------------------")
                _isLoading.postValue(true)
            }.collect{result ->
                   result.onSuccess {
                       _peekBalanceResponse.postValue(it)
                       _isLoading.postValue(false)
                       Log.e(TAG, "onSucess----------------------------")
                   }.onFailure {
                       _isLoading.postValue(false)
                       _errorMessage.postValue(it.localizedMessage)
                       Log.e(TAG, "onFailure----------------------------")
                   }
            }
        }
    }
}