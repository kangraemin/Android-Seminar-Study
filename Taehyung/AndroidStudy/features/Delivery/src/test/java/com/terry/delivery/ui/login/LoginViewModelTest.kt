package com.terry.delivery.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.terry.delivery.ImmediateSchedulerRule
import com.terry.delivery.data.repository.FakeDeliveryRepositoryImplTest
import com.terry.delivery.getOrAwaitValueAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
 * Created by Taehyung Kim on 2021-10-13
 */
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var schedulerRule = ImmediateSchedulerRule()

    lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(FakeDeliveryRepositoryImplTest())
    }

    @After
    fun teardown() {

    }

    @Test
    fun loginSuccess_returnsTrue() {
        viewModel.login("test_id", "test_password")

        val value = viewModel.loginResult.getOrAwaitValueAndroidTest()

        Truth.assertThat(value).isTrue()
    }
}