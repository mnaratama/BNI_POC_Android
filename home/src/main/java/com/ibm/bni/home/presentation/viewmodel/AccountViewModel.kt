package com.ibm.bni.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import com.ibm.bni.home.data.remote.model.ListAccountModel
import com.ibm.bni.home.data.remote.model.ListTransactionModel
import com.ibm.bni.home.data.remote.model.PointModel
import com.ibm.bni.home.domain.repository.AccountRepository
import com.ibm.bni.home.domain.repository.HomeRepository
import com.ibm.bni.home.domain.usecase.AccountUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AccountViewModel"

class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val accountUseCase: AccountUseCase,
    private val repositoryHome: HomeRepository,
) : BaseViewModel() {

    private val _peekBalanceResponse = MutableLiveData<ListTransactionModel>()
    val peekBalanceResponse: LiveData<ListTransactionModel> = _peekBalanceResponse

    private val _listAccountResponse = MutableLiveData<ListAccountModel>()
    val listAccountResponse: LiveData<ListAccountModel> = _listAccountResponse

    private val _getPointResponse = MutableLiveData<PointModel>()
    val getPointResponse: LiveData<PointModel> = _getPointResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun listTransaction(accountNo:String) {
        coroutineScope.launch {
            repository.listTransaction(accountNo).onStart {
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

    fun listAccount(accountNo:String) {
        coroutineScope.launch {
            repositoryHome.listAccount(accountNo).onStart {
                Log.e(TAG, "Onstart----------------------------")
                _isLoading.postValue(true)
            }.collect{result ->
                result.onSuccess {
                    _listAccountResponse.postValue(it)
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
            repositoryHome.getPoint(accountNo).onStart {
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