package com.mrozon.core_mvvm

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.mrozon.core_mvvm.databinding.FragmentMyInfoDialogBinding

class MyInfoDialog: DialogFragment() {

    companion object {
        private var action: () -> Unit = {}
        const val TAG = "MyInfoDialog"

        private const val KEY_TYPE = "KEY_TYPE"
        private const val KEY_MESSAGE = "KEY_MESSAGE"

        fun newInstance(type: MyInfoDialogType, message: String, action:()->Unit): MyInfoDialog {
            val args = bundleOf(KEY_TYPE to type.ordinal, KEY_MESSAGE to message)
            this.action = action
            val fragment = MyInfoDialog()
            fragment.arguments = args
            return fragment
        }

        fun newInstance(type: MyInfoDialogType, message: String): MyInfoDialog {
            val args = bundleOf(KEY_TYPE to type.ordinal, KEY_MESSAGE to message)
            val fragment = MyInfoDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentMyInfoDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyInfoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.setCanceledOnTouchOutside(false)
        val decorView = dialog?.window?.decorView
        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            decorView,
            PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
            PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f),
            PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f)
        )
        scaleDown.duration = 400
        scaleDown.start()
    }

    private fun setupClickListeners() {
        binding.buttonCloseDialog.setOnClickListener {
            dismiss()
            action()
        }
    }

    private fun setupView() {
        val type = MyInfoDialogType.values()[arguments?.getInt(KEY_TYPE,0)?:0]
        if(type==MyInfoDialogType.INFO){
            binding.ivTypeIcon.setImageResource(R.drawable.ic_success)
        }
        else
        {
            binding.ivTypeIcon.setImageResource(R.drawable.ic_error)
        }
        binding.tvMessage.text = arguments?.getString(KEY_MESSAGE,"")
    }

}

enum class MyInfoDialogType {
    INFO,
    ERROR
}