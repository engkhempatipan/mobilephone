package com.mvpclean.scb.remote

import com.mvpclean.scb.remote.model.ImageModel
import com.mvpclean.scb.remote.model.MobileModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("mobiles/")
    fun getMobiles(): Single<List<MobileModel>?>

    @GET("mobiles/{id}/images")
    fun getImagesByMobileId(@Path("id") id: String): Single<List<ImageModel>?>

}
