package com.terry.delivery.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.terry.delivery.getOrAwaitValue
import com.terry.delivery.remote.LoginService
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/*
 * Created by Taehyung Kim on 2021-09-08
 */
class LoginViewModelTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var loginViewModel: LoginViewModel

    // Given
    @Before
    fun initialize() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        loginViewModel = LoginViewModel()
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `UserName, Password에 대한 액세스 토큰 요청하기`() {
        // When
        loginViewModel.getAccessToken("delivery", "dev_baemin")

        // Then
        loginViewModel.token.getOrAwaitValue()
        Assert.assertNotNull(loginViewModel.token.value?.access)
    }
}

