package com.mvpclean.scb.ui.favoritelist.presenter

import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.base.BasePresenter
import com.mvpclean.scb.ui.base.BaseView

interface FavoriteContract {

    interface View : BaseView<Presenter> {
        fun showProgress()
        fun updateList(mobiles: List<Mobile>)
        fun updateFavoriteList(listFavorite: List<String>)
    }

    interface Presenter : BasePresenter {
        fun getFavorite()
        fun deleteFavorite(id: String)
        fun sortData(key: String)
    }

}