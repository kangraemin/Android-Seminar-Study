package com.terry.testcode.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.terry.testcode.ResultModel
import com.terry.testcode.databinding.ActivityCommonMainBinding
import com.terry.testcode.mvvm.viewmodel.MvvmViewModel
import com.terry.testcode.mvvm.viewmodel.MvvmViewModelFactory

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MvvmMainActivity : AppCompatActivity() {

    private val resultModel: ResultModel by lazy { ResultModel() }

    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModelFactory(resultModel)
    }

    private var binding: ActivityCommonMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonMainBinding.inflate(layoutInflater)

        binding?.let { binding ->
            setContentView(binding.root)
            bindView(binding)
        }

        observeViewModelData()
    }

    private fun bindView(binding: ActivityCommonMainBinding) {
        binding.makeResultButton.setOnClickListener {
            viewModel.saveResult(
                binding.firstInputEditText.text.toString(),
                binding.secondInputEditText.text.toString()
            )
        }
    }

    private fun observeViewModelData() {
        viewModel.processState.observe(this) { isProcessing ->
            binding?.let { binding ->
                binding.progressBar.isVisible = isProcessing
            }
        }

        viewModel.resultData.observe(this) { result ->
            binding?.let { binding ->
                binding.resultTextView.text = result
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}