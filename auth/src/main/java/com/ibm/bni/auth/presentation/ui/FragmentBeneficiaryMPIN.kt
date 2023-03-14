package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentBeneficiaryMpinBinding
import com.ibm.bni.auth.databinding.FragmentBeneficiarySuccessBinding
import com.ibm.bni.auth.presentation.viewmodel.BeneficiaryMPINViewModel
import com.ibm.bni.auth.presentation.viewmodel.BeneficiarySuccessViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
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
        makeTransfer()
    }

    private fun makeTransfer() {
        _binding?.submit?.setOnClickListener{
            val  navController : NavController = Navigation.findNavController(requireActivity(), R.id.authFrameContainer)
            navController.navigate(R.id.action_mobileFragment_to_mobileInputFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}