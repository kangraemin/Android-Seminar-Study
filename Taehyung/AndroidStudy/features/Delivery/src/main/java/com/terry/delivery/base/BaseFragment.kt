package com.terry.delivery.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

/*
 * Created by Taehyung Kim on 2021-08-29
 */
abstract class BaseFragment<B : ViewBinding>(
    private val inflate: FragmentInflater<B>
) : Fragment() {

    private var binding: B? = null

    protected val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflate.invoke(inflater, container, false).also { B ->
            this.binding = B
        }

        return binding.root
    }

    fun getViewBinding() = binding ?: throw Throwable("Binding is null !!")

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}