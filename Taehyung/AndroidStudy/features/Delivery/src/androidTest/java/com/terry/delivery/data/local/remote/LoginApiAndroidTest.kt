package com.terry.delivery.data.local.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.terry.delivery.ImmediateSchedulerRuleAndroidTest
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.model.LoginInfo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-10-14
 */
@SmallTest
@HiltAndroidTest
class LoginApiAndroidTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var schedulerRule = ImmediateSchedulerRuleAndroidTest()

    @Inject
    lateinit var loginApi: LoginApi

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
    }

    @Test
    fun getAccessTokenWithValidInfo_returnsTrue() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")

        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        println("token -> ${token.access}")
        Truth.assertThat(token.access).isNotEmpty()
        Truth.assertThat(token.refresh).isNotEmpty()
    }
}