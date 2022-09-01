package com.sangtb.androidlibrary.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding
import com.sangtb.androidlibrary.base.action.IActivityApplication
import com.sangtb.androidlibrary.utils.ClassUtils.getAnnotation
import com.sangtb.androidlibrary.utils.CommonHelper
import com.sangtb.androidlibrary.utils.ToastManager

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), IActivityApplication {
    @get:LayoutRes
    abstract val layoutId: Int
    abstract val fragmentContainerView: Int

    protected lateinit var binding: VB
        private set
    protected lateinit var controller: NavController
        private set
    private lateinit var navHostFragment: NavHostFragment
        private set
    override var rootView: View? = null

    private val toastManager : ToastManager by lazy { ToastManager.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(fragmentContainerView) as NavHostFragment
        controller = navHostFragment.findNavController()
        setupActionBarWithNavController(controller)
        supportActionBar?.hide()

        toastManager.errorThrowable.observe(this) {
            CommonHelper.makeToast(this,it.message, TOAST_DURATION)
        }

        setUpActivityApplication()
        setUpObserve()
    }

    protected abstract fun setUpObserve()

    protected fun isPermissionGrant() : Boolean{
        permissions?.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun getListPermissionDenied() =
        permissions?.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }?.toTypedArray()


    protected open fun requestPermission(list: Array<out String>, requestCode : Int) {
        ActivityCompat.requestPermissions(
            this,
            list, requestCode
        )
    }

    private fun setUpActivityApplication() {
        rootView = binding.root
    }

    companion object{
        const val TOAST_DURATION = 1000
    }
}