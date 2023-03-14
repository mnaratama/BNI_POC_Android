package com.ibm.bniapp.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel

class SplashViewModel : BaseViewModel() {

    val animationCompleted : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean> ()
    }

    private val _userId =MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    fun setUserId(newData:String){
        _userId.postValue(newData)
    }

    init {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            animationCompleted.postValue(true)
        }, 5000)
    }
}