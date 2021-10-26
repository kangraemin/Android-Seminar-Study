package com.terry.delivery.data.repository

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.terry.delivery.ImmediateSchedulerRuleAndroidTest
import com.terry.delivery.data.local.AppDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.SearchApi
import com.terry.delivery.data.remote.model.LoginInfo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*
 * Created by Taehyung Kim on 2021-10-25
 */
@SmallTest
@HiltAndroidTest
class SearchRepositoryImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var schedulerRule = ImmediateSchedulerRuleAndroidTest()

    @Inject
    @Named("test_delivery_database")
    lateinit var deliveryDatabase: AppDatabase

    @Inject
    lateinit var loginApi: LoginApi

    @Inject
    lateinit var searchApi: SearchApi

    lateinit var localTokenDao: LocalTokenDao

    lateinit var searchRepositoryImpl: SearchRepositoryImpl

    @Before
    fun setup() {
        hiltRule.inject()
        localTokenDao = deliveryDatabase.getTokenDao()
        searchRepositoryImpl = SearchRepositoryImpl(searchApi)
    }

    @After
    fun teardown() {
        deliveryDatabase.close()
    }

    @Test
    fun searchItemWithValidInfo_returnsTrue() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        val result = searchRepositoryImpl.searchWithKeyword(
            token.access!!,
            "떡볶이",
            1
        ).blockingGet()

        Truth.assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun searchItemWithInvalidInfo_returnsFalse() {
        val result = searchRepositoryImpl.searchWithKeyword(
            "InvalidToken",
            "떡볶이",
            1
        ).blockingGet()

        Truth.assertThat(result.isFailure).isTrue()
    }
}