package com.terry.delivery.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.filters.SmallTest
import com.terry.delivery.data.local.AppDatabase
import com.terry.delivery.data.local.model.LocalToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*
 * Created by Taehyung Kim on 2021-10-12
 */
@SmallTest
@HiltAndroidTest
class LocalTokenDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_delivery_database")
    lateinit var deliveryDatabase: AppDatabase

    private lateinit var localTokenDao: LocalTokenDao

    @Before
    fun setup() {
        hiltRule.inject()
        localTokenDao = deliveryDatabase.getTokenDao()
    }

    @After
    fun teardown() {
        deliveryDatabase.close()
    }

    @Test
    fun saveTokens() {
        val testToken =
            LocalToken(id = 0, accessToken = "test_token", refreshToken = "test_refresh_token")

        localTokenDao.saveTokens(testToken).blockingAwait()

        localTokenDao.getLocalToken().test().assertValue { token ->
            (token.accessToken == testToken.accessToken) && (token.refreshToken == testToken.refreshToken)
        }
    }

    @Test
    fun deleteAllTokens() {
        val testToken =
            LocalToken(id = 0, accessToken = "test_token", refreshToken = "test_refresh_token")

        localTokenDao.saveTokens(testToken).blockingAwait()

        localTokenDao.getLocalToken().subscribe({
            localTokenDao
                .deleteAllTokens()
                .blockingAwait()
        }, { it.printStackTrace() })

        localTokenDao.getLocalToken().test().assertNoValues()
    }
}