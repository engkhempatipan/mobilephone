package com.mvpclean.scb.ui.favoritelist.presenter

import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.util.sortBy
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class FavoritePresenter @Inject constructor(
    private val view: FavoriteContract.View,
    private val getCachedFavoriteListUseCase: SingleUseCase<List<Mobile>, Void?>,
    private val deleteCachedFavoriteByIdUseCase: SingleUseCase<Boolean, String>,
    private val getCachedSortByUseCase: SingleUseCase<String, Void?>
) : FavoriteContract.Presenter {

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
        getFavorite()
    }

    override fun stop() {
        getCachedFavoriteListUseCase.dispose()
        deleteCachedFavoriteByIdUseCase.dispose()
        getCachedSortByUseCase.dispose()
    }

    private fun updateMobileList(mobileList: List<Mobile>) {
        this.mobiles = mobileList
        sortData(sortByKey)
    }

    override fun getFavorite() {
        getCachedFavoriteListUseCase.execute(object : DisposableSingleObserver<List<Mobile>>() {
            override fun onSuccess(mobiles: List<Mobile>) {
                updateMobileList(mobiles)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    override fun sortData(key: String) {
        sortByKey = key
        mobiles?.let {
            it.sortBy(sortByKey)?.let { sortList ->
                view.updateList(sortList)
            }
        }
    }

    override fun deleteFavorite(id: String) {
        deleteCachedFavoriteByIdUseCase.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                getFavorite()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, id)
    }

}