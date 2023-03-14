package com.ibm.bniapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
import com.ibm.bniapp.databinding.FragmentRecurringBinding
import com.ibm.bniapp.presentation.viewmodel.RecurringViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecurringFragment : BaseFragment<RecurringViewModel>() {
    private var _binding: FragmentRecurringBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var recurringViewModel : RecurringViewModel

    override fun getInjectViewModel(): RecurringViewModel {
        return recurringViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecurringBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnCreate.setOnClickListener { view->
                findNavController().navigate(R.id.action_recurringFragment_to_fragmentTransferSuccess)
                return@setOnClickListener
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}