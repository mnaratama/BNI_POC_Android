package com.ibm.bni.auth.presentation.ui;

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ibm.bni.auth.R
import com.ibm.bni.auth.data.remote.model.GreetingData
import com.ibm.bni.auth.data.remote.model.Transaction
import com.ibm.bni.auth.databinding.FragmentHomeBinding
import com.ibm.bni.auth.presentation.Constant
import com.ibm.bni.auth.presentation.ui.adapter.GreetingAdapter
import com.ibm.bni.auth.presentation.ui.adapter.NewAndHotDealAdapter
import com.ibm.bni.auth.presentation.viewmodel.PreLoginViewModel
import com.ibm.bni.core.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<PreLoginViewModel>() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var adapter = NewAndHotDealAdapter()

    private var adapterGreeting = GreetingAdapter()

    @Inject
    lateinit var preLoginViewModel: PreLoginViewModel

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            imageSlider.setSliderAdapter(adapter)
            imageSliderGreeting.setSliderAdapter(adapterGreeting)
            adapterGreeting.setData(arrayListOf(GreetingData("1",1),GreetingData("2",1)))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnCheckBalance.setOnLongClickListener { view ->
                findNavController().navigate(R.id.action_preLoginFragment_to_peekBalanceFragment)
                return@setOnLongClickListener true
            }
            contQuickLink.btnLoginNew.setOnClickListener {
                val resultCheckBiometric = checkBiometric()
                EventBus().postSticky(Transaction(22.2, "1231", "asdfa"))
                if (resultCheckBiometric.first) {
                    when (resultCheckBiometric.second) {
                        "face" -> findNavController().navigate(R.id.action_preLoginFragment_to_fingerPrintLoginFragment)
                        "finger" -> findNavController().navigate(R.id.action_preLoginFragment_to_fingerPrintLoginFragment)

                    }
                } else {
                    when (resultCheckBiometric.second) {
                        "notAllowed" -> {

                        }
                        else -> {
                            findNavController().navigate(R.id.action_homeFragment_to_loginUserIdPasswordFragment)
                        }
                    }
                }
            }
            prefs = requireActivity().getSharedPreferences(
                Constant.SHARE_PREF_APP_NAME,
                Context.MODE_PRIVATE
            )
            imgUserPic.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.avatar_dummy))

            val userId = prefs.getString(Constant.USER_ID, "")
            userId?.let {
                viewModel.setUserId(userId)
            }

            viewModel.getNewAndHotDeal()
            imgSetting.setOnClickListener {
                findNavController().navigate(R.id.action_preLoginFragment_to_preLoginConfigurationFragment)
            }
            observeViewModels()
        }
    }

    private fun observeViewModels() {
        viewModel.newHotDealResult.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.userData.observe(viewLifecycleOwner) {
            it?.let {
                val edit = prefs.edit()
                binding.txtUsername.text = it.userData.accountname
                edit.putString(Constant.ACCOUNT_NO,it.userData.accountno).apply()
                edit.putString(Constant.CIF_ACCOUNT,it.userData.cif).apply()
                edit.putString(Constant.ACCOUNT_NAME, it.userData.accountname).apply()
            }
        }
        viewModel.userId.observe(viewLifecycleOwner) {
            viewModel.getUserData(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.isVisible = it
            }
        }
    }

    override fun getInjectViewModel(): PreLoginViewModel {
        return preLoginViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkBiometric(): Pair<Boolean, String> {
        var returnResult: Pair<Boolean, String>
        val keyGuard =
            requireActivity().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyGuard.isDeviceSecure) {
            returnResult = Pair(false, "")
            return returnResult
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            returnResult = Pair(false, "notAllowed")
            return returnResult
        }

        val haveFaceRecognition =
            if (requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_FACE)) true else false

        returnResult = if (haveFaceRecognition) {
            Pair(true, "face")
        } else {
            Pair(false, "face")
        }
        val haveFingerPrint =
            requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)

        returnResult = if (haveFingerPrint) {
            Pair(true, "finger")
        } else {
            Pair(false, "finger")
        }

        return returnResult
    }

}