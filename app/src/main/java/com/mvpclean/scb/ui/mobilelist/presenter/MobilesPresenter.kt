package com.mvpclean.scb.ui.mobilelist.presenter

import com.mvpclean.scb.domain.interactor.CompletableUseCase
import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.util.sortBy
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MobilesPresenter @Inject constructor(
    private val view: MobilesContract.View,
    private val getMobilesUseCase: SingleUseCase<List<Mobile>, Void?>,
    private val saveCachedFavoriteUseCase: CompletableUseCase<Mobile>,
    private val getCachedFavoriteListUseCase: SingleUseCase<List<Mobile>, Void?>,
    private val deleteCachedFavoriteByIdUseCase: CompletableUseCase<String>,
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
        saveCachedFavoriteUseCase.unsubscribe()
        getCachedFavoriteListUseCase.dispose()
        deleteCachedFavoriteByIdUseCase.unsubscribe()
        getCachedSortByUseCase.dispose()
    }

    override fun getMobiles() {
        getMobilesUseCase.execute(GetMobilesSubscriber())
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
        saveCachedFavoriteUseCase.execute(mobile).doOnComplete {
            getFavoriteList()
        }.subscribe()
    }

    override fun onUnFavoriteClick(id: String) {
        deleteCachedFavoriteByIdUseCase.execute(id).doOnComplete {
            getFavoriteList()
        }.subscribe()
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

}