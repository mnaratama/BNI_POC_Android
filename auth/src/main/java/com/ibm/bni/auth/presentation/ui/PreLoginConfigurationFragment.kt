package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.databinding.FragmentPreloginConfigurationBinding
import com.ibm.bni.auth.presentation.viewmodel.PreLoginConfigurationViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PreLoginConfigurationFragment : BaseFragment<PreLoginConfigurationViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentPreloginConfigurationBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var preLoginConfigurationViewModel: PreLoginConfigurationViewModel

    override fun getInjectViewModel(): PreLoginConfigurationViewModel {
        return preLoginConfigurationViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreloginConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            toolbar.backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}