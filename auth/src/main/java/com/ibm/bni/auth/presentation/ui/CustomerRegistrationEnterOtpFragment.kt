package com.ibm.bni.auth.presentation.ui;

import android.graphics.ColorFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentCustomerregistrationenterotpBinding
import com.ibm.bni.auth.presentation.viewmodel.CustomerRegistrationEnterOtpViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerRegistrationEnterOtpFragment : BaseFragment<CustomerRegistrationEnterOtpViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentCustomerregistrationenterotpBinding? = null

    private val binding get() = _binding!!

    private lateinit var mobileNo : String

    @Inject
    lateinit var customerRegistrationEnterOtpViewModel: CustomerRegistrationEnterOtpViewModel

    override fun getInjectViewModel(): CustomerRegistrationEnterOtpViewModel {
        return customerRegistrationEnterOtpViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerregistrationenterotpBinding.inflate(inflater, container, false)
        mobileNo = arguments?.getString("mobile").toString()
        initView()
        return binding.root
    }

    private fun initView() {
        binding.txtMobileNumber.text = getString(R.string.cr_enter_otp_sent_to) + mobileNo + getString(R.string.closing_bracket)
        timer.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeView()
    }

    private fun observeView() {
        viewModel.isSuccess.observe(requireActivity(), Observer { status->
            if(status)
                findNavController().navigate(R.id.action_customerRegistrationEnterOtpFragment_to_customerRegistrationEnterCredentialFragment)
            else{
                Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isOTPSendSuccess.observe(requireActivity(), Observer { status->
            if(status) {
                Toast.makeText(requireActivity(), "OTP Generated Successfully", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.errorMessage.observe(requireActivity()){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage()
            }
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
                if(TextUtils.isEmpty(etOTP.text.toString())){
                    Toast.makeText(requireActivity(), "Please input mobile number", Toast.LENGTH_LONG).show()
                }
                else {
                    viewModel.validateOTP(etOTP.text.toString())
                }
                return@setOnClickListener
            }

            tvOtpResend.setOnClickListener{
                viewModel.generateOTP(mobileNo)
                timer.start()
            }

            etOTP.addTextChangedListener(afterTextChanged = {
                if((etOTP.text.toString()).length ==6){
                    btnNext.isEnabled = true
                }else{
                    btnNext.isEnabled = false
                }

            })

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val timer = object: CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.tvOtpTimer.text = ""+millisUntilFinished/1000 +" sec"
            binding.tvOtpResend.isEnabled = false
            binding.tvOtpResend.setTextColor(ContextCompat.getColor(requireActivity(), R.color.disable_button))
        }

        override fun onFinish() {
            binding.tvOtpTimer.text = "0 sec"
            binding.tvOtpResend.isEnabled = true
            binding.tvOtpResend.setTextColor(ContextCompat.getColor(requireActivity(), R.color.blue))
        }
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}