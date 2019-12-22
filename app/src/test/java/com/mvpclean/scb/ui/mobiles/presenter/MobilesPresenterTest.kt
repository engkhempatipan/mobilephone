package com.mvpclean.scb.ui.mobiles.presenter

import com.mvpclean.scb.domain.interactor.deletefavorite.DeleteFavoriteById
import com.mvpclean.scb.domain.interactor.getFavoritelist.GetFavoriteList
import com.mvpclean.scb.domain.interactor.mobiles.GetMobiles
import com.mvpclean.scb.domain.interactor.savemobile.Favorite
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.mobilelist.presenter.MobilesContract
import com.mvpclean.scb.ui.mobilelist.presenter.MobilesPresenter
import com.mvpclean.scb.ui.mobiles.MobilesFactory.Factory.makeListMobile
import com.mvpclean.scb.ui.test.DataFactory.Factory.randomUuid
import com.mvpclean.scb.util.sortBy
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class MobilesPresenterTest {

    lateinit var presenter: MobilesPresenter
    lateinit var view: MobilesContract.View

    lateinit var getMobilesUseCase: GetMobiles
    lateinit var saveCachedFavoriteUseCase: Favorite
    lateinit var getCachedFavoriteListUseCase: GetFavoriteList
    lateinit var deleteCachedFavoriteByIdUseCase: DeleteFavoriteById
    lateinit var getCachedSortByUseCase: GetSortBy

    lateinit var captorSetCachedSortByUseCase: KArgumentCaptor<Completable>
    lateinit var captorGetCachedSortByUseCase: KArgumentCaptor<DisposableSingleObserver<String>>
    lateinit var captorGetCachedFavoriteListUseCase: KArgumentCaptor<Completable>

    lateinit var captorGetMobilesUseCase: KArgumentCaptor<DisposableSingleObserver<List<Mobile>>>

    @Before
    fun setUp() {
        view = mock()
        getMobilesUseCase = mock()
        saveCachedFavoriteUseCase = mock()
        getCachedFavoriteListUseCase = mock()
        deleteCachedFavoriteByIdUseCase = mock()
        getCachedSortByUseCase = mock()

        captorGetCachedFavoriteListUseCase = argumentCaptor()
        captorSetCachedSortByUseCase = argumentCaptor()
        captorGetCachedSortByUseCase = argumentCaptor()
        captorGetMobilesUseCase = argumentCaptor()

        presenter = MobilesPresenter(
            view,
            getMobilesUseCase,
            saveCachedFavoriteUseCase,
            getCachedFavoriteListUseCase,
            deleteCachedFavoriteByIdUseCase,
            getCachedSortByUseCase
        )
    }

    @Test
    fun getMobileList_onSuccess() {
        // GIVEN
        val listMobile = makeListMobile(10)
        // WHEN
        presenter.getMobiles()

        // THEN
        verify(getMobilesUseCase).execute(captorGetMobilesUseCase.capture(), eq(null))
        captorGetMobilesUseCase.firstValue.onSuccess(listMobile)
        verify(view).hideLoadingDialog()
        assertEquals(presenter.mobiles, listMobile)
    }

    @Test
    fun getMobileList_onError() {
        // WHEN
        presenter.getMobiles()

        // THEN
        verify(getMobilesUseCase).execute(captorGetMobilesUseCase.capture(), eq(null))
        captorGetMobilesUseCase.firstValue.onError(Throwable())
        verify(view).hideLoadingDialog()
    }

    @Test
    fun sortData(){
        // GIVEN
        val listMobile = makeListMobile(10)
        val key  = randomUuid()
        presenter.mobiles = listMobile
        val sortList = listMobile.sortBy(key)

        // WHEN
        presenter.sortData(key)

        // THEN
        assertEquals(presenter.sortByKey,key)
        verify(view).updateList(sortList!!)
    }

    @Test
    fun onStart(){
        // WHEN
        presenter.start()

        // THEN
        verify(view).showLoadingDialog()
    }

}