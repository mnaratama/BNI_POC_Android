package com.ibm.bni.auth.presentation.ui

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double?.toLocalString() : String {
    return try {
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID")) as DecimalFormat
        format.applyPattern("#,##0")
        format.format(this)
    }catch (e: Exception) {
        this.toString()
    }

}