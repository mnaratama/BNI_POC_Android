package com.ibm.bni.home.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibm.bni.core.presentation.ui.BaseFragment
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import com.ibm.bni.home.data.remote.model.ListAccountResult
import com.ibm.bni.home.databinding.DialogManageBinding
import com.ibm.bni.home.databinding.FragmentHomeNavBinding
import com.ibm.bni.home.presentation.adapter.*
import com.ibm.bni.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel>() {
    private var binding: FragmentHomeNavBinding? = null
    lateinit var bindingManage: DialogManageBinding
    lateinit var adapterQuickLinks: AdapterQuickLinks
    private var contentsQuickLinks = arrayListOf<QuickLinksModel>()

    lateinit var adapterQuickLinksManage: AdapterQuickLinksManage
    lateinit var adapterQuickLinksManageFalse: AdapterQuickLinksManageFalse
    private val contentsQuickLinksManage = arrayListOf<QuickLinksModel>()
    private val contentsQuickLinksManageFalse = arrayListOf<QuickLinksModel>()

//    private lateinit var adapterListAccount : AdapterListAccount
    lateinit var adapterListAccount2: AdapterListAccount2

    private var adapterProduct = AdapterListProduct()
    private var adapterPromo = AdapterListPromo()

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var prefs: SharedPreferences
    override fun getInjectViewModel(): HomeViewModel {
        return homeViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefs = requireActivity().getSharedPreferences(
            "bni",
            Context.MODE_PRIVATE
        )
        binding = FragmentHomeNavBinding.inflate(inflater, container, false)
        val edit = prefs
        edit.getString("accountname","Reza")?.let {
            binding?.lblAccountname?.text = "Hello, $it"
        }

        binding?.bNav?.imageSliderProduct?.setSliderAdapter(adapterProduct)
        adapterProduct.setData(arrayListOf(
            "Tired of paying international transfer fees? There’s another way!",
            "Tired of paying international transfer fees? There’s another way!",
            "Tired of paying international transfer fees? There’s another way!"))

        binding?.bNav?.imageSliderPromo?.setSliderAdapter(adapterPromo)
        adapterPromo.setData(arrayListOf(
            "Pay with QRIS at any TOKO KOPI TUKU and enjoy 10% OFF!",
            "Pay with QRIS at any TOKO KOPI TUKU and enjoy 10% OFF!",
            "Pay with QRIS at any TOKO KOPI TUKU and enjoy 10% OFF!"))

        // note: get point
        prefs.getString("cifAccount","CIF-00001")?.let {
            viewModel.getPoint(it)
        }
//        viewModel.getPoint("CIF-00000")
        viewModel.getPointResponse.observe(viewLifecycleOwner) {
            it?.let {
                binding?.point?.text = it.pointBalance.toLocalString()
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            binding?.btnManage?.setOnClickListener {
                val bottomDialog = context?.let { it1 -> BottomSheetDialog(it1) }
                bindingManage = DialogManageBinding.inflate(layoutInflater)
                bottomDialog?.setContentView(bindingManage.root)
                bindingManage.btnClose.setOnClickListener {
                    contentsQuickLinks = contentsQuickLinksManage
                    adapterQuickLinks.setListItem(contentsQuickLinks)
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
                    override fun onItemClicked(data: QuickLinksModel) {
                        if (data.id == 1) {
                            EventBus.getDefault().post("Epic2")
                        }
                    }
                }
            )

            binding?.rvQuicklinks?.run {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterQuickLinks
            }

            // note: list account
            prefs.getString("cifAccount","CIF-00001")?.let {
                viewModel.listAccount(it)
            }
//            viewModel.listAccount("CIF-00001")
            viewModel.peekBalanceResponse.observe(viewLifecycleOwner) {
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

            adapterListAccount2 = AdapterListAccount2(requireContext(),
                object : AdapterListAccount2.ClickCallback {
                    override fun onItemClicked(data: ListAccountResult) {
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        transaction?.replace(R.id.container, AccountFragment())
                        transaction?.disallowAddToBackStack()
                        transaction?.commit()
                    }
                }
            )

            binding?.rvAccount?.run {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterListAccount2
            }

            setDataQuicklinks()

        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}