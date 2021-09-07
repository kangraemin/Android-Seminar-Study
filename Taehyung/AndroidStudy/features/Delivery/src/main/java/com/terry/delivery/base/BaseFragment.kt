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

    protected var binding: B? = null
        private set

    protected val compositeDisposable = CompositeDisposable()

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

    override fun onDestroyView() {
        binding = null
        compositeDisposable.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}