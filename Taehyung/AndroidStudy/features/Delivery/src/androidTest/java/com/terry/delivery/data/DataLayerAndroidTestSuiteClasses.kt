package com.terry.delivery.data

import com.terry.delivery.data.local.dao.LocalTokenDaoTest
import com.terry.delivery.data.remote.LoginApiAndroidTest
import com.terry.delivery.data.remote.SearchApiAndroidTest
import com.terry.delivery.data.repository.DeliveryRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/*
 * Created by Taehyung Kim on 2021-10-17
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    LocalTokenDaoTest::class,
    LoginApiAndroidTest::class,
    SearchApiAndroidTest::class,
    DeliveryRepositoryImplTest::class
)
class DataLayerAndroidTestSuiteClasses