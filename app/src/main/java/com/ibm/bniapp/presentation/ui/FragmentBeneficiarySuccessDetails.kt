package com.ibm.bniapp.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bniapp.databinding.FragmentBeneficiarySuccessDetailsBinding
import com.ibm.bni.auth.presentation.viewmodel.BeneficiarySuccessDetailsViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setup.setOnClickListener { view->
                findNavController().navigate(R.id.action_fragmentBeneficiarySuccessDetails_to_recurringFragment)
                return@setOnClickListener
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}