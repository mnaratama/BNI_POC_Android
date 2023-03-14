package com.ibm.bniapp

import android.app.Application
import android.content.Intent
import android.util.Log
import com.ibm.bni.auth.presentation.ui.HomeActivity
import com.ibm.bni.home.presentation.NavbarActivity
import com.ibm.bniapp.presentation.ui.FundTransferActivity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


private const val TAG = "BniApplication"
@HiltAndroidApp
class BniApplication : Application() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun getMessage(data:String){
        Log.d(TAG, "getMessage:$data ")
        when(data){
            "Epic3"->{
                val init = Intent(this, NavbarActivity::class.java)
                init.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(init)
            }
            "Epic2"->{
                val init = Intent(this, FundTransferActivity::class.java)
                init.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(init)
            }
           /* "Registration"->{
                val init = Intent(this, com.ibm.bni.home.presentation.NavbarActivity::class)
                init.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(init)
            }
            "Epic2"->{
                val init = Intent(this, com.ibm.bni.home.presentation.NavbarActivity::class)
                init.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(init)
            }
            "Epic4"->{
                val init = Intent(this, com.ibm.bni.home.presentation.NavbarActivity::class)
                init.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(init)
            }*/
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        EventBus.getDefault().unregister(this)
    }

}