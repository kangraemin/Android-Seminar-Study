package com.terry.testcode.mvc

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

class MvcMainActivity : AppCompatActivity() {

    private val mvcController = MvcController()

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
        // User Action
        makeResultButton.setOnClickListener {
            showProgress()

            // Controller : action 에 대한 처리 진행(Update UI)
            mvcController.saveResult(
                first = firstEditText.text.toString(),
                second = secondEditText.text.toString()
            ) { isSuccess, resultData ->
                // Update UI
                hideProgress()

                if (isSuccess) {
                    showResultData(resultData)
                }
            }
        }
    }

    private fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = true
        }
    }

    private fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = false
        }
    }

    private fun showResultData(resultData: ArrayList<String>) {
        Handler(Looper.getMainLooper()).post {
            // Model Changed
            resultTextView.text = getAllResult(resultData)
        }
    }

    private fun getAllResult(resultData: ArrayList<String>): String =
        resultData.reduce { total, s -> total + s }
}
