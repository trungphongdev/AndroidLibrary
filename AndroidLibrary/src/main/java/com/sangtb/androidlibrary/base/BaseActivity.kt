package com.sangtb.androidlibrary.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    protected val listPermissionRequest = mutableListOf<String>()

    protected lateinit var binding: VB
    protected lateinit var controller: NavController
    private lateinit var navHostFragment: NavHostFragment
    override var rootView: View? = null

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

    protected fun isPermissionGrant() : Boolean{
        var check = true
        if(permissions != null){
            listPermissionRequest.clear()
            for (permission in permissions!!){
                if( ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                    listPermissionRequest.add(permission)
                    check = false
                }
            }
        }else{
            check = false
        }
        return check
    }

    private fun setUpActivityApplication() {
        rootView = binding.root
    }
}