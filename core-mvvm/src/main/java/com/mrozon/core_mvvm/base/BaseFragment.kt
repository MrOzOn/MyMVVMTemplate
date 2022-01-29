package com.mrozon.core_mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.mrozon.core_mvvm.MyInfoDialog
import com.mrozon.core_mvvm.MyInfoDialogType

abstract class BaseFragment<T : ViewDataBinding>: Fragment(){
    protected var _binding: T? = null
    protected val binding get() = _binding!!

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun subscribeUi() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = getLayoutId()
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        subscribeUi()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showError(message: String, action:()->Unit) {
        MyInfoDialog.newInstance(MyInfoDialogType.ERROR,message, action)
            .show(requireActivity().supportFragmentManager, MyInfoDialog.TAG)
    }

    fun showError(message: String) {
        MyInfoDialog.newInstance(MyInfoDialogType.ERROR,message)
            .show(requireActivity().supportFragmentManager, MyInfoDialog.TAG)
    }

    fun showInfo(message: String, action:()->Unit) {
        MyInfoDialog.newInstance(MyInfoDialogType.INFO,message, action)
            .show(requireActivity().supportFragmentManager, MyInfoDialog.TAG)
    }

    fun show(message: String) {
        val snackbar = Snackbar.make(binding.root,message,LENGTH_LONG)
        snackbar.show()
    }
}