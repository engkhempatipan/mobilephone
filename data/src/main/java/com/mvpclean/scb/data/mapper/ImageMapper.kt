package com.mvpclean.scb.data.mapper

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.domain.model.Image
import javax.inject.Inject


open class ImageMapper @Inject constructor() :
    Mapper<ImageEntity, Image> {
    override fun mapFromEntity(type: ImageEntity): Image {
        return Image(
            type.id ?: "",
            type.url ?: "",
            type.mobile_id ?: ""
        )
    }

    override fun mapToEntity(type: Image): ImageEntity {
        return ImageEntity(
            type.id ?: "",
            type.url ?: "",
            type.mobile_id ?: ""
        )
    }

}