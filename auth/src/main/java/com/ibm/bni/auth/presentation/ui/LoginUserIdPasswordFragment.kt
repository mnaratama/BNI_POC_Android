package com.ibm.bni.auth.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.data.remote.model.LoginRequest
import com.ibm.bni.auth.databinding.FragmentUserIdPasswordLoginBinding
import com.ibm.bni.auth.presentation.viewmodel.LoginUserIdPasswordViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bni.core.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class LoginUserIdPasswordFragment : BaseFragment<LoginUserIdPasswordViewModel>() {

    private var _binding: FragmentUserIdPasswordLoginBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var loginUserIdPasswordViewModel: LoginUserIdPasswordViewModel

    override fun getInjectViewModel(): LoginUserIdPasswordViewModel {
        return loginUserIdPasswordViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserIdPasswordLoginBinding.inflate(inflater, container, false)
        with(binding){
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                val acId = editTextUserId.text.toString()
                if(acId == ""){
                    editTextUserId.error = "Please fill the Username"
                    return@setOnClickListener
                }
                editTextUserId.error = null
                val password = editTextPassword.text.toString()
                if(password == ""){
                    editTextPassword.error = "Please fill the Password"
                    return@setOnClickListener
                }
                editTextPassword.error = null

                viewModel.login(
                    LoginRequest(acId,password)
                )
            }
        }
        observeViewModels()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}