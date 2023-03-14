package com.ibm.bni.auth.presentation.ui;

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.databinding.FragmentPeekbalanceBinding
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.ui.adapter.RecentTransactionAdapter
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bni.auth.presentation.viewmodel.PeekBalanceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeekBalanceFragment : BaseFragment<PeekBalanceViewModel>() {
    // chang with you layout binding name
    private var _binding: FragmentPeekbalanceBinding? = null

    private val binding get() = _binding!!

    private val adapter = RecentTransactionAdapter()

    @Inject
    lateinit var peekBalanceViewModel: PeekBalanceViewModel

    override fun getInjectViewModel(): PeekBalanceViewModel {
        return peekBalanceViewModel
    }

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeekbalanceBinding.inflate(inflater, container, false)
        with(binding){
            rvTransaction.adapter = adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            rootPeekBalance.setOnClickListener {
                findNavController().popBackStack()
            }
            contPeekBalance.setOnClickListener {
                findNavController().popBackStack()
            }
            rvTransaction.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        prefs = requireActivity().getSharedPreferences(
            Constant.SHARE_PREF_APP_NAME,
            Context.MODE_PRIVATE
        )
        val userId = prefs.getString(Constant.ACCOUNT_NO, "")
        userId?.let {
            viewModel.setUserId(userId)
        }
        observerViewModel()
    }

    private fun observerViewModel(){
        viewModel.peekBalanceResponse.observe(viewLifecycleOwner){
            if(it == null){
                Toast.makeText(requireContext(), "There is no data to show", Toast.LENGTH_SHORT).show()
            }else{
                it?.let {
                    adapter.setData(it.transactions)
                    with(binding){
                        lbl1.text = it.productType
                        lblBalance.text = "IDR "+it.currentBalance.toLocalString()
                        imgCardCont.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dummy_card))
                    }
                }
            }

        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.isVisible = it
            }
        }
        viewModel.userId.observe(viewLifecycleOwner) {
            viewModel.peekBalance(it)
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