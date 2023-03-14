package com.ibm.bni.core.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseBottomSheetFragment <VM : BaseViewModel> : BottomSheetDialogFragment() {

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