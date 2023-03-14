package com.ibm.bniapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.bni.auth.data.remote.model.CurrencyList
import com.ibm.bni.auth.domain.repository.TransactionRepository
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "Currency Calculator View Model"

class CurrencyCalculatorViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :BaseViewModel() {

     val isSuccess = MutableLiveData<Boolean>()
     val currencyList = MutableLiveData<List<CurrencyList>>()

    fun getCurrencyList() {
        viewModelScope.launch {
            transactionRepository.getCurrency().onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect {result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------${it?.message}")
                    isSuccess.value = it?.success
                    currencyList.value = it?.currency
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    isSuccess.value = false
                }
            }
        }
    }
}