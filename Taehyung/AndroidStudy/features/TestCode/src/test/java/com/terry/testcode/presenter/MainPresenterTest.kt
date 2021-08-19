package com.terry.testcode.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.terry.testcode.mvp.presenter.Contract
import com.terry.testcode.mvp.presenter.MainPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/*
 * Created by Taehyung Kim on 2021-08-18
 */
class MainPresenterTest {

    private lateinit var view: Contract.View

    private lateinit var presenter: MainPresenter

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        view = Mockito.mock(Contract.View::class.java)
        presenter = MainPresenter(view)
    }

    @Test
    fun saveResult() {
        // Given
        val inOrder = Mockito.inOrder(view)
        val first = "30"
        val second = "60"

        // When
        presenter.saveResult(first, second)

        // Then
        inOrder.verify(view).showProgress()
        Thread.sleep(850)
        inOrder.verify(view).hideProgress()
        inOrder.verify(view).showResultData("90\n")
    }
}