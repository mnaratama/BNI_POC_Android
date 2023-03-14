package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentIntroBinding
import com.ibm.bni.auth.presentation.viewmodel.IntroViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : BaseFragment<IntroViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentIntroBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var introViewModel: IntroViewModel

    override fun getInjectViewModel(): IntroViewModel {
        return introViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        doLogin()
        return binding.root
    }

    private fun doLogin() {
        _binding?.start?.setOnClickListener{
            val  navController : NavController = Navigation.findNavController(requireActivity(), R.id.authFrameContainer)
            navController.navigate(R.id.action_mobileFragment_to_mobileInputFragment)
            /*val bottomSheetFragment = ChooseBeneficaryFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}