package com.mvpclean.scb.ui.main.presenter

import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.domain.interactor.sortby.SetSortBy
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test


class MainPresenterTest {

    lateinit var presenter: MainPresenter
    lateinit var view: MainContract.View
    lateinit var getCachedSortByUseCase: GetSortBy
    lateinit var setCachedSortByUseCase: SetSortBy
    lateinit var threadExecutor: ThreadExecutor

    lateinit var captorSetCachedSortByUseCase: KArgumentCaptor<Completable>
    lateinit var captorGetCachedSortByUseCase: KArgumentCaptor<DisposableSingleObserver<String>>

    @Before
    fun setUp() {
        view = mock()
        getCachedSortByUseCase = mock()
        setCachedSortByUseCase = mock()
        threadExecutor = mock()
        captorSetCachedSortByUseCase = argumentCaptor()
        captorGetCachedSortByUseCase = argumentCaptor()
        presenter = MainPresenter(view, setCachedSortByUseCase, getCachedSortByUseCase)
    }

    @Test
    fun onSortMenuClicked() {
        // WHEN
        presenter.onSortMenuClicked()

        // THEN
        verify(view).showSortByDialog(presenter.sortByKey)
    }

}