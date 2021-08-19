package com.terry.testcode.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.terry.testcode.ResultModel
import com.terry.testcode.getOrAwaitValue
import com.terry.testcode.mvvm.viewmodel.MvvmViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test

/*
 * Created by Taehyung Kim on 2021-08-18
 */
class MvvmViewModelTest {

    /*
     * This rule runs all Architecture Components-related background jobs in the same thread
     * so that the test results happen synchronously, and in a repeatable order.
     * When you write tests that include testing LiveData, use this rule!
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun saveResult() {
        // Given
        val viewModel = MvvmViewModel(ResultModel())

        // When
        viewModel.saveResult("20", "30")

        // Then
        val value = viewModel.resultData.getOrAwaitValue()

        assertThat(value, `is`("50\n"))
    }
}