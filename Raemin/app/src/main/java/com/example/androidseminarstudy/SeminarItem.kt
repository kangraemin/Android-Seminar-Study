package com.example.androidseminarstudy

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.androidseminarstudy.tdd.TestDrivenDevelopmentActivity

enum class SeminarItem(val seminarActivity: Class<out AppCompatActivity>, @StringRes val title: Int) {
    FIRST_WEEK_TEST_DRIVEN_DEVELOPMENT(TestDrivenDevelopmentActivity::class.java, R.string.first_week_test_driven_development_title)
}