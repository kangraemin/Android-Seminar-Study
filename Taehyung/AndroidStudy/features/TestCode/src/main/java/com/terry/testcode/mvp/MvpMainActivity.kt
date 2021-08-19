package com.terry.testcode.mvp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.terry.testcode.R
import com.terry.testcode.mvp.presenter.Contract
import com.terry.testcode.mvp.presenter.MainPresenter

class MvpMainActivity : AppCompatActivity(), Contract.View {

    private val mainPresenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    private val makeResultButton by lazy {
        findViewById<Button>(R.id.makeResultButton)
    }
    private val firstEditText by lazy {
        findViewById<EditText>(R.id.firstInputEditText)
    }
    private val secondEditText by lazy {
        findViewById<EditText>(R.id.secondInputEditText)
    }
    private val resultTextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }
    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_main)

        bindViews()
    }

    private fun bindViews() {
        makeResultButton.setOnClickListener {
            mainPresenter.saveResult(
                first = firstEditText.text.toString(),
                second = secondEditText.text.toString()
            )
        }
    }

    override fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = true
        }
    }

    override fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = false
        }
    }

    override fun showResultData(resultData: String) {
        Handler(Looper.getMainLooper()).post {
            resultTextView.text = resultData
        }
    }

}