package com.ibm.bniapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
import com.ibm.bniapp.databinding.FragmentConfirmTransactionBinding
import com.ibm.bniapp.presentation.viewmodel.ConfirmTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmTransactionFragment : BaseFragment<ConfirmTransactionViewModel>() {
    // chang with you layout binding name

    private var _binding: FragmentConfirmTransactionBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var confirmTransactionViewModel: ConfirmTransactionViewModel

    override fun getInjectViewModel(): ConfirmTransactionViewModel {
        return confirmTransactionViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backBtn.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }

            btnNext.setOnClickListener { view->
                findNavController().navigate(R.id.action_confirmTransactionFragment_to_fragmentBeneficiaryMPIN)
                return@setOnClickListener
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}