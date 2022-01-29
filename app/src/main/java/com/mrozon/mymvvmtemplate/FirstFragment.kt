package com.mrozon.mymvvmtemplate

import com.mrozon.core_mvvm.base.BaseFragment
import com.mrozon.mymvvmtemplate.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment: BaseFragment<FragmentFirstBinding>(){

    override fun getLayoutId() = R.layout.fragment_first

    override fun subscribeUi() {
        TODO("Not yet implemented")
    }
}