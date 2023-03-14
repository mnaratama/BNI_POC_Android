package com.ibm.bni.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.PointModel
import com.ibm.bni.home.domain.repository.HomeRepository
import com.ibm.bni.home.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {
    private val _peekBalanceResponse = MutableLiveData<ListAccountModel>()
    val peekBalanceResponse: LiveData<ListAccountModel> = _peekBalanceResponse

    private val _getPointResponse = MutableLiveData<PointModel>()
    val getPointResponse: LiveData<PointModel> = _getPointResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun listAccount(accountNo:String) {
        coroutineScope.launch {
            repository.listAccount(accountNo).onStart {
                Log.e(TAG, "Onstart----------------------------")
                _isLoading.postValue(true)
            }.collect{result ->
                   result.onSuccess {
                       _peekBalanceResponse.postValue(it)
                       _isLoading.postValue(false)
                       Log.e(TAG, "onSucess----------------------------")
                   }.onFailure {
                       _isLoading.postValue(false)
                       Log.e(TAG, "onFailure----------------------------")
                   }
            }
        }
    }

    fun getPoint(accountNo:String) {
        coroutineScope.launch {
            repository.getPoint(accountNo).onStart {
                Log.e(TAG, "Onstart----------------------------")
                _isLoading.postValue(true)
            }.collect{result ->
                result.onSuccess {
                    _getPointResponse.postValue(it)
                    _isLoading.postValue(false)
                    Log.e(TAG, "onSucess----------------------------")
                }.onFailure {
                    _isLoading.postValue(false)
                    Log.e(TAG, "onFailure----------------------------")
                }
            }
        }
    }

}