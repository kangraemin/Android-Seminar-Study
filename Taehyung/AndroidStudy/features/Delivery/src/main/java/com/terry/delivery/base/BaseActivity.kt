package com.terry.delivery.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

/*
 * Created by Taehyung Kim on 2021-08-29
 */
abstract class BaseActivity<B : ViewBinding>(
    private val inflate: ActivityInflater<B>
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    protected val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate.invoke(layoutInflater)

        setContentView(binding.root)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}