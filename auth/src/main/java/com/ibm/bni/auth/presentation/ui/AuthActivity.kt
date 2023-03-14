package com.ibm.bni.auth.presentation.ui

import android.os.Bundle
import android.view.WindowManager
import com.ibm.bni.auth.R
import com.ibm.bni.auth.presentation.viewmodel.AuthViewModel
import com.ibm.bni.core.presentation.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : BaseActivity<AuthViewModel>() {

    @Inject
    lateinit var authViewModel : AuthViewModel


    override fun setupViews(savedInstanceState: Bundle?) {
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_auth
    }
}