package com.ibm.bni.core.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseFragment <VM : BaseViewModel> : Fragment() {

    lateinit var viewModel: VM
    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onDetach() {
        super.onDetach()
        coroutineScope.coroutineContext.cancelChildren()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getInjectViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun getInjectViewModel(): VM

    fun isViewLive() = isAdded && view != null

    protected open fun initViews() {

    }

}