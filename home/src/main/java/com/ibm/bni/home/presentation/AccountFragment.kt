package com.ibm.bni.home.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import com.ibm.bni.home.data.remote.model.ListAccountResult
import com.ibm.bni.home.data.remote.model.ListTransactionResult
import com.ibm.bni.home.databinding.DialogManageBinding
import com.ibm.bni.home.databinding.FragmentAccount2Binding
import com.ibm.bni.home.presentation.adapter.*
import com.ibm.bni.home.presentation.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : BaseFragment<AccountViewModel>() {
    private var binding: FragmentAccount2Binding? = null
    var quickLinks: Boolean? = false
    lateinit var adapterListTransaction: AdapterListTransaction

    @Inject
    lateinit var accountViewModel : AccountViewModel

    private lateinit var prefs: SharedPreferences

    lateinit var adapterListAccount2: AdapterListAccountSlider

    lateinit var adapterQuickLinksManage: AdapterQuickLinksManage
    lateinit var adapterQuickLinksManageFalse: AdapterQuickLinksManageFalse
    private val contentsQuickLinksManage = arrayListOf<QuickLinksModel>()
    private val contentsQuickLinksManageFalse = arrayListOf<QuickLinksModel>()
    lateinit var bindingManage: DialogManageBinding

    lateinit var adapterQuickLinks: AdapterQuickLinks
    private var contentsQuickLinks = arrayListOf<QuickLinksModel>()

    override fun getInjectViewModel(): AccountViewModel {
        return accountViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccount2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireActivity().getSharedPreferences(
            "bni",
            Context.MODE_PRIVATE
        )
        with(binding){

            if (quickLinks == false) {
                binding?.llQuicklinks?.visibility = View.GONE
                binding?.chevron?.setImageResource(R.drawable.chevron_down)
            } else {
                binding?.llQuicklinks?.visibility = View.VISIBLE
                binding?.chevron?.setImageResource(R.drawable.chevron_up)
            }

            binding?.btnShow?.setOnClickListener {
                if (quickLinks == false) {
                    quickLinks = true
                    binding?.llQuicklinks?.visibility = View.GONE
                    binding?.chevron?.setImageResource(R.drawable.chevron_down)
                } else {
                    quickLinks = false
                    binding?.llQuicklinks?.visibility = View.VISIBLE
                    binding?.chevron?.setImageResource(R.drawable.chevron_up)
                }
            }

            // note: list transaction
            prefs.getString("accountNo","000001")?.let {
                viewModel.listTransaction(it)
            }
//            viewModel.listTransaction("000001")
            viewModel.peekBalanceResponse.observe(viewLifecycleOwner) {
                it?.let {
                    adapterListTransaction.setListItem(it.transactions)
                    with(binding) {}
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner){
                it?.let {
//                    binding.progressBar.isVisible = it
                }
            }

            adapterListTransaction = AdapterListTransaction(requireContext(),
                object : AdapterListTransaction.ClickCallback {
                    override fun onItemClicked(data: ListTransactionResult) {}
                }
            )

            binding?.rvTransaction?.run {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = adapterListTransaction
            }

            // note: list account

            prefs.getString("cifAccount","CIF-00001")?.let {
                viewModel.listAccount(it)
            }
            viewModel.listAccountResponse.observe(viewLifecycleOwner) {
                it?.let {
                    adapterListAccount2.setListItem(it.accounts)
                    with(binding) {}
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner){
                it?.let {
//                    binding.progressBar.isVisible = it
                }
            }

            adapterListAccount2 = AdapterListAccountSlider(requireContext(),
                object : AdapterListAccountSlider.ClickCallback {
                    override fun onItemClicked(data: ListAccountResult) {}
                }
            )

            binding?.rvAccount?.run {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterListAccount2
            }

            binding?.btnBack?.setOnClickListener {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, HomeFragment())
                transaction?.addToBackStack(null)
                transaction?.commit()
            }

            // note: get point
//            viewModel.getPoint("CIF-00000")
            prefs.getString("cifAccount","CIF-00001")?.let {
                viewModel.getPoint(it)
            }
            viewModel.getPointResponse.observe(viewLifecycleOwner) {
                it?.let {
                    binding?.point?.text = it.pointBalance.toLocalString()
                }
            }

            binding?.btnManage?.setOnClickListener {
                val bottomDialog = context?.let { it1 -> BottomSheetDialog(it1) }
                bindingManage = DialogManageBinding.inflate(layoutInflater)
                bottomDialog?.setContentView(bindingManage.root)
                bindingManage.btnClose.setOnClickListener {
//                    contentsQuickLinks = contentsQuickLinksManage
//                    adapterQuickLinks.setListItem(contentsQuickLinks)
                    bottomDialog?.hide()
                }

                adapterQuickLinksManage = AdapterQuickLinksManage(requireContext(),
                    object : AdapterQuickLinksManage.ClickCallback {
                        override fun onItemClicked(data: QuickLinksModel) {
                            data.status = false
                            contentsQuickLinksManage.remove(data)
                            contentsQuickLinksManageFalse.add(data)
                            adapterQuickLinksManage.setListItem(contentsQuickLinksManage)
                            adapterQuickLinksManageFalse.setListItem(contentsQuickLinksManageFalse)
                        }
                    }
                )

                adapterQuickLinksManageFalse = AdapterQuickLinksManageFalse(requireContext(),
                    object : AdapterQuickLinksManageFalse.ClickCallback {
                        override fun onItemClicked(data: QuickLinksModel) {
                            data.status = true
                            contentsQuickLinksManage.add(data)
                            contentsQuickLinksManageFalse.remove(data)
                            adapterQuickLinksManage.setListItem(contentsQuickLinksManage)
                            adapterQuickLinksManageFalse.setListItem(contentsQuickLinksManageFalse)

                        }
                    }
                )

                bindingManage.rvQuicklinksTrue.run {
                    layoutManager = GridLayoutManager(requireContext(), 4)
                    adapter = adapterQuickLinksManage
                }

                bindingManage.rvQuicklinksFalse.run {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = adapterQuickLinksManageFalse
                }

                setDataQuicklinksManage()

                bottomDialog?.show()
            }

            adapterQuickLinks = AdapterQuickLinks(requireContext(),
                object : AdapterQuickLinks.ClickCallback {
                    override fun onItemClicked(data: QuickLinksModel) {}
                }
            )

            binding?.rvQuicklinks?.run {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterQuickLinks
            }

            setDataQuicklinks()

        }
    }

    private fun setDataQuicklinksManage() {
        contentsQuickLinksManage.clear()
        contentsQuickLinksManageFalse.clear()

        val data = arrayListOf<QuickLinksModel>()
        data.add(QuickLinksModel(1, "International Transfer", "", true))
        data.add(QuickLinksModel(2, "Pay Credit Card", "", true))
        data.add(QuickLinksModel(3, "Bill Payment", "", true))
        data.add(QuickLinksModel(4, "Cardless Withdrawl", "", true))
        data.add(QuickLinksModel(5, "Mutual Funds", "", true))
        data.add(QuickLinksModel(6, "POIN+ Homepage", "", true))
        data.add(QuickLinksModel(7, "Book Transport", "", true))
        data.add(QuickLinksModel(8, "Book Hotel", "", false))
        data.add(QuickLinksModel(9, "e-Commerce", "", false))
        data.add(QuickLinksModel(10, "Insurance", "", false))
        data.forEach {
            if (it.status){
                contentsQuickLinksManage.add(it)
            } else {
                contentsQuickLinksManageFalse.add(it)
            }
        }
        adapterQuickLinksManage.setListItem(contentsQuickLinksManage)
        adapterQuickLinksManageFalse.setListItem(contentsQuickLinksManageFalse)
    }

    private fun setDataQuicklinks() {
        val data = arrayListOf<QuickLinksModel>()
        data.add(QuickLinksModel(1, "International Transfer", "", true))
        data.add(QuickLinksModel(2, "Pay Credit Card", "", true))
        data.add(QuickLinksModel(3, "Bill Payment", "", true))
        data.add(QuickLinksModel(4, "Cardless Withdrawl", "", true))
        data.add(QuickLinksModel(5, "Mutual Funds", "", true))
        data.add(QuickLinksModel(6, "POIN+ Homepage", "", true))
        data.add(QuickLinksModel(7, "Book Transport", "", true))
        data.add(QuickLinksModel(8, "Book Hotel", "", false))
        data.add(QuickLinksModel(9, "e-Commerce", "", false))
        data.add(QuickLinksModel(10, "Insurance", "", false))
        data.forEach {
            if (it.status) {
                contentsQuickLinks.add(it)
            }
        }
        adapterQuickLinks.setListItem(contentsQuickLinks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}