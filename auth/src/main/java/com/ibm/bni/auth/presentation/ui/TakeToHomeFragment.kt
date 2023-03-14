package com.ibm.bni.auth.presentation.ui;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibm.bni.auth.databinding.FragmentTakeToHomeBinding
import com.ibm.bni.auth.presentation.viewmodel.TakeToHomeViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TakeToHomeFragment : BaseFragment<TakeToHomeViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentTakeToHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var takeToHomeViewModel: TakeToHomeViewModel

    override fun getInjectViewModel(): TakeToHomeViewModel {
        return takeToHomeViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTakeToHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnNext.setOnClickListener { view->
                startActivity(Intent(activity,HomeActivity::class.java))
                requireActivity().finish()
                return@setOnClickListener
            }

            ivBackButton.setOnClickListener{ requireActivity().finish() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}