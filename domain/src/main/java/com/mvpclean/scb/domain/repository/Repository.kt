package com.mvpclean.scb.domain.repository

import com.mvpclean.scb.domain.model.Image
import com.mvpclean.scb.domain.model.Mobile
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun getMobiles(): Single<List<Mobile>>

    fun getImagesByMobileId(id: String): Single<List<Image>>

    fun saveMobile(mobile: Mobile): Completable

    fun deleteMobile(id: String): Completable

    fun getFavoriteList(): Single<List<Mobile>>

    fun setSortBy(key:String):Completable

    fun getSortBy():Single<String>

}