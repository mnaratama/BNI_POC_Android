package com.ibm.bni.core.presentation.ui

import android.annotation.TargetApi
import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseActivity <T : BaseViewModel> : AppCompatActivity() {

    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    abstract fun setupViews(savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setupViews(savedInstanceState)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity, background : Drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = activity.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = activity.resources.getColor(android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }
    }


    fun showAlert(title : String, message : String, button1 : String, button2 : String, isCancellable : Boolean = true,
                  showCancel : Boolean = true, successBlock : () -> Unit) {
        try {
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage(message)
            builder1.setTitle(title)
            builder1.setCancelable(isCancellable)
            builder1.setPositiveButton(button1) { _, _ ->
                successBlock()
            }
            if (showCancel) {
                builder1.setNegativeButton(button2
                ) { dialog, id ->
                    dialog.cancel()
                }
            }
            val alert11 = builder1.create()
            alert11.show()
        } catch (e: Exception) {

        }
    }


}