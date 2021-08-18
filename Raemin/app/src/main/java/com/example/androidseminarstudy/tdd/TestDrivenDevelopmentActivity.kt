package com.example.androidseminarstudy.tdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.example.androidseminarstudy.R

class TestDrivenDevelopmentActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var tvPasswordRating: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_driven_development)

        etPassword = findViewById(R.id.et_password)
        tvPasswordRating = findViewById(R.id.tv_password_rating)

        etPassword.doAfterTextChanged {
            tvPasswordRating.text = String.format(getString(R.string.first_week_test_driven_development_password_rating_format), TextUtils.getPasswordRating(it.toString()))
        }
    }
}