package com.ibm.bni.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.auth.data.remote.model.GetUserDataResult
import com.ibm.bni.auth.data.remote.model.NewAndHotDealResult
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import com.ibm.bni.auth.domain.usecase.GetNewAndHotDealUseCase
import com.ibm.bni.auth.domain.usecase.GetRegisteredUserUseCase
import com.ibm.bni.auth.domain.usecase.SeeBalanceUseCase
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PreLoginViewModel"

class PreLoginViewModel @Inject constructor(
    private val repository: PreLoginRepository,
    private val getNewAndHotDealUseCase: GetNewAndHotDealUseCase,
    private val getRegisteredUserUseCase: GetRegisteredUserUseCase
) :
    BaseViewModel() {
    private val _newHotDealResult = MutableLiveData<ArrayList<NewAndHotDealResult>>()
    val newHotDealResult: LiveData<ArrayList<NewAndHotDealResult>> = _newHotDealResult

    private val _getUserDataResult = MutableLiveData<GetUserDataResult>()
    val userData: LiveData<GetUserDataResult> = _getUserDataResult

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserId(newData: String) {
        _userId.postValue(newData)
    }

    fun getNewAndHotDeal() {
        coroutineScope.launch {
            getNewAndHotDealUseCase().onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect { result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess---------------------------- ${it?.id}")
                    var datas = arrayListOf<NewAndHotDealResult>()
                    datas.add(
                        NewAndHotDealResult(
                            "1",
                            1,
                            "Tired of paying international transfer fees? There’s another way!"
                        )
                    )
                    datas.add(
                        NewAndHotDealResult(
                            "2",
                            2,
                            "Pay with QRIS at any TOKO KOPI TUKU and enjoy 10% OFF!"
                        )
                    )
                    datas.add(
                        NewAndHotDealResult(
                            "3",
                            3,
                            "Pay with BNI debit card at any COLDSTONE ice cream and enjoy 10% OFF!"
                        )
                    )
                    _newHotDealResult.postValue(datas)
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                }
            }
        }
    }

    fun getRegisteredUser() {
        coroutineScope.launch {
            getRegisteredUserUseCase().onStart {
                Log.e(TAG, "onStart----------------------------")
            }.collect { result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------")
                    var datas = arrayListOf<NewAndHotDealResult>()
                    /*for(i in 1..4){
                        datas.add(
                            NewAndHotDealResult(
                                "Get IDR 100,000 OFF when you purchase ticket fot BNI UI Half Marathon 2023+$i"
                            ))
                    }*/
                    _newHotDealResult.postValue(datas)
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                }
            }
        }
    }

    fun getUserData(userId: String) {
        coroutineScope.launch {
            repository.getUserData(userId).onStart {
                Log.e(TAG, "onStart----------------------------")
                _isLoading.postValue(true)
            }.collect { result ->
                result.onSuccess {
                    Log.e(TAG, "onSuccess----------------------------")
                    if (it?.userData != null) {
                        _getUserDataResult.postValue(it)
                    }
                    _isLoading.postValue(false)
                }.onFailure {
                    Log.e(TAG, "onFailure----------------------------")
                    _isLoading.postValue(false)
                }
            }
        }
    }

}