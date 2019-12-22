package com.mvpclean.scb.ui.favoritelist.adapter

interface CustomItemTouchHelperListener {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}