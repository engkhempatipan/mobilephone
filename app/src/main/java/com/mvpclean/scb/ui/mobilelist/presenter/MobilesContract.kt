package com.mvpclean.scb.ui.mobilelist.presenter

import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.base.BasePresenter
import com.mvpclean.scb.ui.base.BaseView

interface MobilesContract {

    interface View : BaseView<Presenter> {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun updateList(mobiles: List<Mobile>)
        fun updateFavoriteList(listFavorite: List<String>)
    }

    interface Presenter : BasePresenter {
        fun getMobiles()
        fun sortData(key:String)
        fun onFavoriteClick(mobile:Mobile)
        fun onUnFavoriteClick(id: String)
    }

}