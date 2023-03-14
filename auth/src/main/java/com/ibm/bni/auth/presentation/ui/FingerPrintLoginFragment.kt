package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.biometric.Util
import com.ibm.bni.auth.data.remote.model.LoginRequest
import com.ibm.bni.auth.databinding.FragmentBiometricFingerBinding
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.viewmodel.BiometricFingerPrintViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class FingerPrintLoginFragment : BaseFragment<BiometricFingerPrintViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentBiometricFingerBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var biometricFingerPrintViewModel: BiometricFingerPrintViewModel

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: PromptInfo

    override fun getInjectViewModel(): BiometricFingerPrintViewModel {
        return biometricFingerPrintViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiometricFingerBinding.inflate(inflater, container, false)
        with(binding) {
            imgFinger.setOnClickListener {

            }
        }
        return binding.root
    }

    private fun observeViewModels(){
        viewModel.isLoading.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.isVisible = it
            }
        }
        viewModel.loginResult.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                EventBus.getDefault().post("Epic3")
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage()
            }
        }
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(requireContext())

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                Util.init(requireActivity())
                val decryptedData = Util.get(Constant.SECRET_USER_ID)
                Log.e("decrypted data","$decryptedData")
                if((decryptedData?:"").contains("-")){
                    val userPass = decryptedData!!.split("-")
                    val loginRequest = LoginRequest(userPass[0],userPass[1])
                    viewModel.login(loginRequest)
                }
                super.onAuthenticationSucceeded(result)
            }
        }

        return BiometricPrompt(this, executor, callback)
    }
    private fun createPromptInfo(): PromptInfo {
        return PromptInfo.Builder()
            .setTitle("Use Biometric for Login")
            .setConfirmationRequired(false)
            .setNegativeButtonText("Cancel")
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLoginWithUserId.setOnClickListener {
                findNavController().navigate(R.id.action_fingerPrintLoginFragment_to_loginUserIdPasswordFragment)
            }
            imgFinger.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
           biometricPrompt =  createBiometricPrompt()
           promptInfo = createPromptInfo()
        }
        observeViewModels()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}