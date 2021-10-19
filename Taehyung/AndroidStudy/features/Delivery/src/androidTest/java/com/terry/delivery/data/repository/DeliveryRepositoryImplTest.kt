package com.terry.delivery.data.repository

import androidx.room.EmptyResultSetException
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
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        localTokenDao.saveTokens(LocalToken(0, token.access!!, token.refresh!!)).blockingGet()

        val localToken = deliveryRepositoryImpl.checkLocalAccessToken().blockingGet()

        Truth.assertThat(localToken).isNotNull()
    }

    @Test
    fun checkLocalAccessTokenWithEmptyLocalAccessToken_returnsFalse() {

        runCatching {
            val localToken = deliveryRepositoryImpl.checkLocalAccessToken().blockingGet()

            Truth.assertThat(localToken.accessToken).isNull()
        }.onSuccess {
            assert(false)
        }.onFailure {
            Truth.assertThat(it).isInstanceOf(EmptyResultSetException::class.java)
        }
    }

    @Test
    fun refreshAccessTokenWithValidAccessToken_returnsTrue() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        localTokenDao.saveTokens(LocalToken(0, token.access!!, token.refresh!!)).blockingGet()

        val refreshToken = deliveryRepositoryImpl.refreshAccessToken().blockingGet()

        Truth.assertThat(refreshToken).isNotNull()
        Truth.assertThat(refreshToken.access).isNotNull()
    }

    @Test
    fun refreshAccessTokenWithInvalidInfo_returnsFalse() {
        val localToken = LocalToken(
            0,
            "invalid access token",
            "invalid refresh token"
        )

        localTokenDao.saveTokens(localToken).blockingGet()

        runCatching {
            val refreshToken = deliveryRepositoryImpl.refreshAccessToken().blockingGet()

            Truth.assertThat(refreshToken.access).isNull()
            Truth.assertThat(refreshToken.code).isEqualTo("token_not_valid")
            Truth.assertThat(refreshToken.detail).isEqualTo("Token is invalid or expired")
        }.onSuccess {
            assert(false)
        }.onFailure {
            Truth.assertThat(it).isInstanceOf(NullPointerException::class.java)
        }

    }
}