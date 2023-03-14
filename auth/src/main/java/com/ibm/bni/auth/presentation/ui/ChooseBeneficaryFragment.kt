package com.ibm.bni.auth.presentation.ui;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.auth.databinding.BottomFragmentChooseBeneficiaryBinding
import com.ibm.bni.auth.presentation.ui.adapter.ReciverListAdapter
import com.ibm.bni.auth.presentation.viewmodel.BenificayViewModel
import com.ibm.bni.core.presentation.ui.BaseBottomSheetFragment
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseBeneficaryFragment : BaseBottomSheetFragment<BenificayViewModel>() {
    // chang with you layout binding name

    private var reciverListAdapter = ReciverListAdapter()

    private var _binding: BottomFragmentChooseBeneficiaryBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var benificayViewModel: BenificayViewModel

    override fun getInjectViewModel(): BenificayViewModel {
        return benificayViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomFragmentChooseBeneficiaryBinding.inflate(inflater, container, false)
        initRV()
        return binding.root
    }

    private fun initRV() {
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        with(binding.receivers){
            isNestedScrollingEnabled = true
            layoutManager = mLayoutManager
            adapter = reciverListAdapter

            reciverListAdapter.onItemClick = { receiverFields ->
                // do something with your item
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerViewModel()
        viewModel.fetchReceivers("")
    }

    private fun observerViewModel(){
        viewModel.receiverFields.observe(requireActivity()){
            it?.let {
                reciverListAdapter.setReceivers(it)
            }
        }
        viewModel.isSuccess.observe(requireActivity()){
            it?.let {
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}