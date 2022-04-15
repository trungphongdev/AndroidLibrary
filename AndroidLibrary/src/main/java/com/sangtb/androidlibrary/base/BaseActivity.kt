package com.sangtb.androidlibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding
import com.sangtb.androidlibrary.base.action.IActivityApplication

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), IActivityApplication {
    @get:LayoutRes
    abstract val layoutId: Int
    abstract val fragmentContainerView: Int

    protected lateinit var binding: VB
    protected lateinit var controller: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(fragmentContainerView) as NavHostFragment
        controller = navHostFragment.findNavController()
        setupActionBarWithNavController(controller)
        supportActionBar?.hide()

        setUpActivityApplication()
    }

    private fun setUpActivityApplication() {
        rootView = binding.root
    }
}