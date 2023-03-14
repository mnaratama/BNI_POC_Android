package com.ibm.bni.auth.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ibm.bni.auth.R
import com.ibm.bni.auth.data.remote.model.Transaction
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    fun onMessageEvent(datas: Transaction?) {
        datas?.let {
            Log.d("EventBus"," homeFragment: $it")
        }
    }
    override fun onStart() {
        super.onStart()
        EventBus().register(this)
    }
    override fun onStop(){
        super.onStop()
        EventBus().unregister(this)
    }
}