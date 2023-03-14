package com.ibm.bniapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.ui.AuthActivity
import com.ibm.bni.auth.presentation.ui.PreLoginActivity
import com.ibm.bni.core.presentation.ui.BaseActivity
import com.ibm.bniapp.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel>() {

    @Inject
    lateinit var splashViewModel : SplashViewModel
    private lateinit var prefs: SharedPreferences

    override fun setupViews(savedInstanceState: Bundle?) {
        val animationObserver = Observer<Boolean> {
            launchAuthFlow()
        }
        prefs = getSharedPreferences(
            Constant.SHARE_PREF_APP_NAME,
            Context.MODE_PRIVATE
        )
        val userId = prefs.getString(Constant.USER_ID, "")
        if(!userId.equals("")) {
            userId?.let {
                splashViewModel.setUserId(userId)
            }
        }
        splashViewModel.animationCompleted.observe(this, animationObserver)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    private fun launchAuthFlow() {

        val userId = splashViewModel.userId.value
        if (userId != null && userId != "") {
            val intent = Intent(this, PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}