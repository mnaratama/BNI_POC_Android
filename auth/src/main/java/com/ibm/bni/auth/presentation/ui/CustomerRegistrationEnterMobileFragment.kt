package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentCustomerregistrationentermobileBinding
import com.ibm.bni.auth.presentation.viewmodel.CustomerRegistrationEnterMobileViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class CustomerRegistrationEnterMobileFragment : BaseFragment<CustomerRegistrationEnterMobileViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentCustomerregistrationentermobileBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var customerRegistrationEnterMobileViewModel: CustomerRegistrationEnterMobileViewModel

    override fun getInjectViewModel(): CustomerRegistrationEnterMobileViewModel {
        return customerRegistrationEnterMobileViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerregistrationentermobileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeView()
    }

    private fun observeView() {
        viewModel.isSuccess.observe(requireActivity(), Observer { status->
            if(status) {
                val bundle = bundleOf("mobile" to binding.etMobileNumber.text.toString())

                findNavController().navigate(R.id.action_customerRegistrationEnterMobileFragment_to_customerRegistrationEnterOtpFragment, bundle)
            }
            else{
                Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            binding.ivBackButton.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }


            etMobileNumber.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus){
                    if(etMobileNumber.text.toString() == ""){
                        etMobileNumber.setText("+62")
                        etMobileNumber.setSelection(etMobileNumber.text.toString().length -1)
                    }
                }
            }

            etMobileNumber.addTextChangedListener(afterTextChanged = {
                if((etMobileNumber.text.toString()).length >= 10){
                    btnNext.isEnabled = true
                }else{
                    btnNext.isEnabled = false
                }
                if(!etMobileNumber.text.toString().contains("+62")){
                    etMobileNumber.setText("+62")
                    etMobileNumber.setSelection(etMobileNumber.text.toString().length)
                }
            })

            btnNext.setOnClickListener { view->
                if(TextUtils.isEmpty(etMobileNumber.text.toString())){
                    etMobileNumberLayout.error = "Please input mobile number"
                }
                else {
                    viewModel.generateMobileNumber(etMobileNumber.text.toString())
                }
                return@setOnClickListener
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}