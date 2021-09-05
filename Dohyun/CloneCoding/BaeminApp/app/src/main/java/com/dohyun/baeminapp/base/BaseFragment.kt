package com.dohyun.baeminapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner
import java.lang.IllegalStateException

abstract class BaseFragment<B : ViewDataBinding>(
        @LayoutRes val layoutId: Int
): Fragment() {
    private var _binding: B? = null
    protected val binding: B?
        get() = _binding

    abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun init()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return onCreateBinding(inflater, container)
            .also { _binding = it }
            .root
            .also { ViewTreeLifecycleOwner.set(it, viewLifecycleOwner) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected fun requireDataBinding(): B {
        if (_binding == null) {
            throw IllegalStateException(
                "BaseFragment $this did not return a Binding from onCreateView"
            )
        }
        return _binding!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showToast(msg: String) =
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}