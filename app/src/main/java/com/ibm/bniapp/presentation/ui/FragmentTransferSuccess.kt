package com.ibm.bniapp.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ibm.bni.auth.R
import com.ibm.bniapp.databinding.FragmentTransferSuccessBinding
import com.ibm.bni.auth.presentation.viewmodel.TransferSuccessViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentTransferSuccess : BaseFragment<TransferSuccessViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentTransferSuccessBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var beneficiarySuccessViewModel: TransferSuccessViewModel

    override fun getInjectViewModel(): TransferSuccessViewModel {
        return beneficiarySuccessViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // makeTransfer()
    }

    private fun makeTransfer() {
        _binding?.makeTransfer?.setOnClickListener{
            val  navController : NavController = Navigation.findNavController(requireActivity(), R.id.authFrameContainer)
            navController.navigate(R.id.action_mobileFragment_to_mobileInputFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}