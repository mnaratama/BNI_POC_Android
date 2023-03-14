package com.ibm.bniapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bniapp.R
import com.ibm.bniapp.databinding.FragmentCurrencyCalculatorBinding
import com.ibm.bniapp.presentation.viewmodel.CurrencyCalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyCalculatorFragment : BaseFragment<CurrencyCalculatorViewModel>() {
    // chang with you layout binding name
    private var currency = arrayListOf<String?>()
    private var baseCountry = arrayListOf<String?>()
    private var currencySymbol = arrayListOf<Any?>()

    private var _binding: FragmentCurrencyCalculatorBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var currencyCalculatorViewModel: CurrencyCalculatorViewModel

    override fun getInjectViewModel(): CurrencyCalculatorViewModel {
        return currencyCalculatorViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBackButton.setOnClickListener { view->
                findNavController().popBackStack()
                return@setOnClickListener
            }

            btnMakeInternationalTransfer.setOnClickListener { view->
                findNavController().navigate(R.id.action_currencyCalculatorFragment_to_chooseBeneficaryFragment_new)
                return@setOnClickListener
            }
        }
        observeView()
        callCurrencyListApi()

    }

    private fun observeView() {
        currencyCalculatorViewModel.isSuccess.observe(requireActivity(), Observer { status->
            if(status)
                currencyCalculatorViewModel.currencyList.observe(requireActivity(), Observer{ currencyList ->
                    currencyList.forEach {
                        currency.add(it.currency)
                        baseCountry.add(it.baseCountry)
                        currencySymbol.add(it.currencysymbol)
                    }

                    val adapterCurrency  = activity?.applicationContext?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,currency) }
                    adapterCurrency?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spnYourSend.adapter = adapterCurrency

                    val adapterBaseCountry  = activity?.applicationContext?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,baseCountry) }
                    adapterBaseCountry?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spnRecipientGets.adapter = adapterBaseCountry
                })
            else{
                Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun callCurrencyListApi() {
        currencyCalculatorViewModel.getCurrencyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}