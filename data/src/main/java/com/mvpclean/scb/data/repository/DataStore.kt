package com.mvpclean.scb.data.repository

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.data.model.MobileEntity
import io.reactivex.Completable
import io.reactivex.Single

interface DataStore {

    fun getMobiles(): Single<List<MobileEntity>>

    fun getImagesByMobileId(id: String): Single<List<ImageEntity>>

    fun getFavoriteList(): Single<List<MobileEntity>>

    fun saveMobile(mobile: MobileEntity): Completable

    fun deleteMobile(id:String):Completable

    fun setSortBy(key:String):Completable

    fun getSortBy():Single<String>

}