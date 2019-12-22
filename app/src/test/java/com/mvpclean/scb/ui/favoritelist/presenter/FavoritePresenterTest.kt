package com.mvpclean.scb.ui.favoritelist.presenter

import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.deletefavorite.DeleteFavoriteById
import com.mvpclean.scb.domain.interactor.getFavoritelist.GetFavoriteList
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.ui.mobiles.MobilesFactory
import com.mvpclean.scb.ui.test.DataFactory
import com.mvpclean.scb.util.sortBy
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class FavoritePresenterTest {

    lateinit var presenter: FavoritePresenter
    lateinit var view: FavoriteContract.View
    lateinit var threadExecutor: ThreadExecutor
    lateinit var getCachedSortByUseCase : GetSortBy
    lateinit var queryFavoriteList: GetFavoriteList
    lateinit var deleteFavoriteById: DeleteFavoriteById

    lateinit var captorSetCachedSortByUseCase: KArgumentCaptor<Completable>
    lateinit var captorGetCachedSortByUseCase: KArgumentCaptor<DisposableSingleObserver<String>>

    @Before
    fun setUp() {
        view = mock()
        queryFavoriteList = mock()
        deleteFavoriteById = mock()
        getCachedSortByUseCase = mock()
        threadExecutor = mock()
        captorSetCachedSortByUseCase = argumentCaptor()
        captorGetCachedSortByUseCase = argumentCaptor()
        presenter = FavoritePresenter(view, queryFavoriteList, deleteFavoriteById,getCachedSortByUseCase)
    }

    @Test
    fun onSortMenuClicked() {
        // GIVEN
        val listMobile = MobilesFactory.makeListMobile(10)
        val key  = DataFactory.randomUuid()
        presenter.mobiles = listMobile
        val sortList = listMobile.sortBy(key)

        // WHEN
        presenter.sortData(key)

        // THEN
        assertEquals(presenter.sortByKey,key)
        verify(view).updateList(sortList!!)
    }

}