package com.ibm.bni.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ibm.bni.home.R
import com.ibm.bni.home.databinding.ActivityNavbarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavbarActivity : AppCompatActivity() {

    lateinit var binding : ActivityNavbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.lifestyle -> {
//                    loadFragment(ChatFragment())
                    true
                }
                R.id.help -> {
//                    loadFragment(SettingFragment())
                    true
                }
                R.id.more -> {
//                    loadFragment(SettingFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}