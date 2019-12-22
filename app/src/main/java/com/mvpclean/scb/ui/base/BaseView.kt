package com.mvpclean.scb.ui.base

interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}