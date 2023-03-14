package com.ibm.bni.auth.presentation.ui;

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.biometric.Util
import com.ibm.bni.auth.data.remote.model.CredentialRequest
import com.ibm.bni.auth.databinding.FragmentCustomerregistrationentercredentialBinding
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.viewmodel.CustomerRegistrationEnterCredentialViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CustomerRegistrationEnterCredentialFragment : BaseFragment<CustomerRegistrationEnterCredentialViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentCustomerregistrationentercredentialBinding? = null

    private val binding get() = _binding!!

    private lateinit var prefs: SharedPreferences

    @Inject
    lateinit var customerRegistrationEnterCredentialViewModel: CustomerRegistrationEnterCredentialViewModel

    override fun getInjectViewModel(): CustomerRegistrationEnterCredentialViewModel {
        return customerRegistrationEnterCredentialViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerregistrationentercredentialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeView()
    }

    private fun observeView() {
        viewModel.isSuccess.observe(requireActivity(), Observer { status->
            if(status)
                saveUserId(binding.etUserId.text.toString(), binding.etUserId.text.toString()+"-"+binding.etPassword.text.toString())
            else{
                Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBackButton.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }

            btnNext.setOnClickListener { view->
                viewModel.validateCredential(CredentialRequest(etUserId.text.toString(), etPassword.text.toString()))
                return@setOnClickListener
            }

            etUserId.addTextChangedListener(afterTextChanged = {
                if((etUserId.text.toString() !="") && (etPassword.text.toString() !="")){
                    btnNext.isEnabled = true
                }else{
                    btnNext.isEnabled = false
                }
            })

            etPassword.addTextChangedListener(afterTextChanged = {
                if((etUserId.text.toString() !="") && (etPassword.text.toString() !="")){
                    btnNext.isEnabled = true
                }else{
                    btnNext.isEnabled = false
                }
            })

        }
    }

    private fun saveUserId(userId: String, data: String) {
        val prefs: SharedPreferences = requireActivity().getSharedPreferences(Constant.SHARE_PREF_APP_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(Constant.USER_ID, userId).apply()
        Util.init(requireActivity())
        Util.save(Constant.SECRET_USER_ID, data)
        prefs.getString(Constant.USER_ID,"")?.let {
            Log.d("TAG_Os", "saveUserId: $it")
        }
        findNavController().navigate(R.id.action_customerRegistrationEnterCredentialFragment_to_peekConfigureFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}