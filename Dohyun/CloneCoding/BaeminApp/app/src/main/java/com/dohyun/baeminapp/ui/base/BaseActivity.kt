package com.dohyun.baeminapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.IllegalStateException

abstract class BaseActivity<B : ViewDataBinding>(
        @LayoutRes val layoutId: Int
) : AppCompatActivity() {
    private var _binding: B? = null
    protected val binding: B?
        get() = _binding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        _binding?.lifecycleOwner = this
    }

    protected fun requireDataBinding(): B {
        if (_binding == null) {
            throw IllegalStateException(
                "BaseActivity $this did not return a Binding from onCreate"
            )
        }
        return _binding!!
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}