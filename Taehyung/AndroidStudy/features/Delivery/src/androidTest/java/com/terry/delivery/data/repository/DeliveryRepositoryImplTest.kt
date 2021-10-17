package com.terry.delivery.data.repository

import androidx.room.EmptyResultSetException
import androidx.test.filters.SmallTest
import com.terry.delivery.ImmediateSchedulerRuleAndroidTest
import com.terry.delivery.data.local.AppDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.SearchApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*
 * Created by Taehyung Kim on 2021-10-17
 */
@SmallTest
@HiltAndroidTest
class DeliveryRepositoryImplTest {

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

    lateinit var deliveryRepositoryImpl: DeliveryRepositoryImpl

    @Before
    fun setup() {
        hiltRule.inject()
        localTokenDao = deliveryDatabase.getTokenDao()
        deliveryRepositoryImpl = DeliveryRepositoryImpl(loginApi, searchApi, localTokenDao)
    }

    @After
    fun teardown() {
        deliveryDatabase.close()
    }

    @Test
    fun loginWithValidInfo_returnsTrue() {
        deliveryRepositoryImpl
            .login("delivery", "dev_baemin")
            .test()
            .assertComplete()
    }

    @Test
    fun loginWithInvalidInfo_returnsFalse() {
        deliveryRepositoryImpl
            .login("invalid_user_name", "dev_baemin")
            .test()
            .assertNotComplete()
    }

    @Test
    fun checkLocalAccessTokenWithValidInfo_returnsTrue() {
        val localToken = LocalToken(0, "access_token_test", "refresh_token_test")

        localTokenDao.saveTokens(localToken).blockingGet()

        deliveryRepositoryImpl
            .checkLocalAccessToken()
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun checkLocalAccessTokenWithEmptyLocalAccessToken_returnsFalse() {
        deliveryRepositoryImpl
            .checkLocalAccessToken()
            .test()
            .assertNotComplete()
    }
}