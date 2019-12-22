package com.mvpclean.scb.remote.model

import com.google.gson.annotations.SerializedName

data class MobileModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("thumbImageURL")
    val thumbImageURL: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rating")
    val rating: String?
)