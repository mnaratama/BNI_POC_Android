package com.ibm.bni.auth.presentation.ui;

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentConfigurePeekBalanceBinding
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.viewmodel.ConfigureViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfigurePeekBalanceFragment : BaseFragment<ConfigureViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentConfigurePeekBalanceBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var configureViewModel: ConfigureViewModel

    override fun getInjectViewModel(): ConfigureViewModel {
        return configureViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurePeekBalanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnNext.setOnClickListener { view->
                findNavController().navigate(R.id.action_peekConfigureFragment_to_takeToHomeFragment)
                return@setOnClickListener
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}