package com.ibm.bniapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibm.bniapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundTransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_transfer)
    }
}