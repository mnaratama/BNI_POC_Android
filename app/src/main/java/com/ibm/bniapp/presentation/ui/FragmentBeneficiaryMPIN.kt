package com.ibm.bniapp.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bniapp.databinding.FragmentBeneficiaryMpinBinding
import com.ibm.bni.auth.presentation.viewmodel.BeneficiaryMPINViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentBeneficiaryMPIN : BaseFragment<BeneficiaryMPINViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentBeneficiaryMpinBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var beneficiaryMPINViewModel: BeneficiaryMPINViewModel

    override fun getInjectViewModel(): BeneficiaryMPINViewModel {
        return beneficiaryMPINViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeneficiaryMpinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBackButton.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }

            submit.setOnClickListener { view->
                findNavController().navigate(R.id.action_fragmentBeneficiaryMPIN_to_fragmentBeneficiarySuccessDetails)
                return@setOnClickListener
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}