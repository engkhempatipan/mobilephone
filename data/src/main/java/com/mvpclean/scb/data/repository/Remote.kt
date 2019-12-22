package com.mvpclean.scb.data.repository

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.data.model.MobileEntity
import io.reactivex.Single

interface Remote {

    fun getMobiles(): Single<List<MobileEntity>>

    fun getImagesByMobileId(id: String): Single<List<ImageEntity>>
}