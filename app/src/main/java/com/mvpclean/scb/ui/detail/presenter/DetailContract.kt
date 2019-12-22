package com.mvpclean.scb.ui.detail.presenter

import com.mvpclean.scb.domain.model.Image
import com.mvpclean.scb.ui.base.BasePresenter
import com.mvpclean.scb.ui.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter> {
        fun setImageAdapter(images: List<Image>)
    }

    interface Presenter : BasePresenter {
        fun getImagesById(id: String)
    }

}