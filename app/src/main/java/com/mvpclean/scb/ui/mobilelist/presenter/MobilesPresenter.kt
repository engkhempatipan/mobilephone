package com.mvpclean.scb.ui.mobilelist.presenter

import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.util.sortBy
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MobilesPresenter @Inject constructor(
    private val view: MobilesContract.View,
    private val getMobilesUseCase: SingleUseCase<List<Mobile>, Void?>,
    private val saveCachedFavoriteUseCase: SingleUseCase<Boolean, Mobile>,
    private val getCachedFavoriteListUseCase: SingleUseCase<List<Mobile>, Void?>,
    private val deleteCachedFavoriteByIdUseCase: SingleUseCase<Boolean, String>,
    private val getCachedSortByUseCase: SingleUseCase<String, Void?>
) : MobilesContract.Presenter {

    var sortByKey = ""

    var mobiles: List<Mobile>? = null

    init {

        view.setPresenter(this)

        getCachedSortByUseCase.execute(object : DisposableSingleObserver<String>() {
            override fun onSuccess(key: String) {
                sortByKey = key
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    override fun start() {
        view.showLoadingDialog()
        getMobiles()
    }

    override fun stop() {
        getMobilesUseCase.dispose()
        getCachedFavoriteListUseCase.dispose()
        deleteCachedFavoriteByIdUseCase.dispose()
        getCachedSortByUseCase.dispose()
    }

    override fun getMobiles() {
        getMobilesUseCase.execute(GetMobilesSubscriber())
    }

    private fun updateMobileList(mobileList: List<Mobile>) {
        view.hideLoadingDialog()
        this.mobiles = mobileList
        sortData(sortByKey)
    }

    override fun sortData(key: String) {
        sortByKey = key
        mobiles?.let {
            it.sortBy(sortByKey)?.let { sortList ->
                view.updateList(sortList)
            }
        }
    }

    private fun updateFavoriteListId(listFavorite: List<String>) {
        view.updateFavoriteList(listFavorite)
        this.mobiles?.let {
            updateMobileList(it)
        }
    }

    override fun onFavoriteClick(mobile: Mobile) {
        saveCachedFavoriteUseCase.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                getFavoriteList()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, mobile)
    }

    override fun onUnFavoriteClick(id: String) {
        deleteCachedFavoriteByIdUseCase.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                getFavoriteList()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, id)
    }

    private fun getFavoriteList() {
        getCachedFavoriteListUseCase.execute(object : DisposableSingleObserver<List<Mobile>>() {
            override fun onSuccess(cachedMobiles: List<Mobile>) {
                val favoriteListId = mutableListOf<String>()
                cachedMobiles.forEach {
                    it.id?.let { id ->
                        favoriteListId.add(id)
                    }
                }
                updateFavoriteListId(favoriteListId)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    inner class GetMobilesSubscriber : DisposableSingleObserver<List<Mobile>>() {
        override fun onSuccess(mobiles: List<Mobile>) {
            updateMobileList(mobiles)
            getFavoriteList()
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            view.hideLoadingDialog()
        }
    }

}