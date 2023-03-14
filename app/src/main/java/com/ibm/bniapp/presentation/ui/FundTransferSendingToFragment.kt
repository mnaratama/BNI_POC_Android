package com.ibm.bniapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
import com.ibm.bniapp.databinding.FragmentFundTransferSendingToBinding
import com.ibm.bniapp.presentation.viewmodel.FundTransferSendingToViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FundTransferSendingToFragment : BaseFragment<FundTransferSendingToViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentFundTransferSendingToBinding? = null

    private val binding get() = _binding!!

    private lateinit var receiverName : String
    private lateinit var receiverBankName : String
    private lateinit var receiverAcNumber : String
    private lateinit var receiverCountryName : String
    private lateinit var receiverCurrency : String

    @Inject
    lateinit var fundTransferSendingToViewModel: FundTransferSendingToViewModel

    override fun getInjectViewModel(): FundTransferSendingToViewModel {
        return fundTransferSendingToViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFundTransferSendingToBinding.inflate(inflater, container, false)
        receiverName = arguments?.getString("receiverName").toString()
        receiverBankName = arguments?.getString("receiverBankName").toString()
        receiverCurrency = arguments?.getString("receiverCurrency").toString()
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding){
            txtNameShort.text = receiverName.replace("^\\s*([a-zA-Z]).*\\s+([a-zA-Z])\\S+$", "$1$2")
            txtName.text = receiverName
            txtBankDetails.text = receiverBankName +" | "+"9368******"
            txtCurrency.text = "IDR" +" - "+receiverCurrency
            spnYourSend.text = "IDR"
            spnRecipientGets.text = receiverCurrency
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBackButton.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }

            btnNext.setOnClickListener { view->
                findNavController().navigate(R.id.action_fundTransferSendingToFragment_to_confirmTransactionFragment)
                return@setOnClickListener
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}