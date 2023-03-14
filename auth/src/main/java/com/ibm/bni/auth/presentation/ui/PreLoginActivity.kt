package com.ibm.bni.auth.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibm.bni.auth.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prelogin)

    }
}