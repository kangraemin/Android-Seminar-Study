package com.terry.delivery.data

import com.terry.delivery.data.remote.LoginApiTest
import com.terry.delivery.data.remote.SearchApiTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/*
 * Created by Taehyung Kim on 2021-10-17
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginApiTest::class,
    SearchApiTest::class
)
class DataLayerTestSuiteClasses