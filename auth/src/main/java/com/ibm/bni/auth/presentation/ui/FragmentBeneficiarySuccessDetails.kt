package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentBeneficiarySuccessDetailsBinding
import com.ibm.bni.auth.presentation.viewmodel.BeneficiarySuccessDetailsViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentBeneficiarySuccessDetails : BaseFragment<BeneficiarySuccessDetailsViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentBeneficiarySuccessDetailsBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var beneficiarySuccessViewModel: BeneficiarySuccessDetailsViewModel

    override fun getInjectViewModel(): BeneficiarySuccessDetailsViewModel {
        return beneficiarySuccessViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeneficiarySuccessDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeTransfer()
    }

    private fun makeTransfer() {
        _binding?.makeTransfer?.setOnClickListener{
            val  navController : NavController = Navigation.findNavController(requireActivity(), R.id.authFrameContainer)
            //navController.navigate(R.id.actionFragmentRecurring)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}