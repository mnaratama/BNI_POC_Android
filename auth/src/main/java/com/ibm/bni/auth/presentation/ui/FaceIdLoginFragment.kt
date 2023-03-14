package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibm.bni.auth.databinding.FragmentBiomatricFaceIdBinding
import com.ibm.bni.auth.presentation.viewmodel.BiometricFaceViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FaceIdLoginFragment : BaseFragment<BiometricFaceViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentBiomatricFaceIdBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var biometricFaceViewModel: BiometricFaceViewModel

    override fun getInjectViewModel(): BiometricFaceViewModel {
        return biometricFaceViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiomatricFaceIdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}