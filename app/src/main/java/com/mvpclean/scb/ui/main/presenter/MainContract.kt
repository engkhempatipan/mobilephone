package com.mvpclean.scb.ui.main.presenter

import com.mvpclean.scb.ui.base.BasePresenter
import com.mvpclean.scb.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showSortByDialog(key: String)
    }

    interface Presenter : BasePresenter {
        fun onSortMenuClicked()
        fun setSortByPriceLowToHigh()
        fun setSortByPriceHighToLow()
        fun setSortByRating()
    }
}