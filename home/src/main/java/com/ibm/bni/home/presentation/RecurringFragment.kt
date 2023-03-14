package com.ibm.bni.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import com.ibm.bni.home.data.remote.model.ListAccountResult
import com.ibm.bni.home.data.remote.model.ListTransactionResult
import com.ibm.bni.home.databinding.DialogManageBinding
import com.ibm.bni.home.databinding.FragmentAccount2Binding
import com.ibm.bni.home.databinding.FragmentRecurringBinding
import com.ibm.bni.home.presentation.adapter.*
import com.ibm.bni.home.presentation.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecurringFragment : BaseFragment<AccountViewModel>() {
    private var binding: FragmentRecurringBinding? = null

    @Inject
    lateinit var accountViewModel : AccountViewModel

    override fun getInjectViewModel(): AccountViewModel {
        return accountViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecurringBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}