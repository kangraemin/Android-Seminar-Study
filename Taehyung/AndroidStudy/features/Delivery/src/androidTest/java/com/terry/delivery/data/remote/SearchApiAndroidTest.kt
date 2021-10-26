package com.terry.delivery.data.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.terry.delivery.ImmediateSchedulerRuleAndroidTest
import com.terry.delivery.data.remote.model.LoginInfo
import com.terry.delivery.util.Const
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-10-17
 */
@SmallTest
@HiltAndroidTest
class SearchApiAndroidTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var schedulerRule = ImmediateSchedulerRuleAndroidTest()

    @Inject
    lateinit var searchApi: SearchApi

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
    fun searchKeywordWithValidInfo_returnsTrue() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        val result = searchApi.searchItem(
            accessToken = "Bearer ${token.access}",
            query = "떡볶이",
            page = 1
        ).blockingGet()

        Truth.assertThat(result.restaurantDto).isNotNull()
        Truth.assertThat(result.restaurantDto).isNotEmpty()
    }

}