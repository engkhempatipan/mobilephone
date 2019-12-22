package com.mvpclean.scb.remote.mapper

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.remote.model.ImageModel
import javax.inject.Inject

open class ImageEntityMapper @Inject constructor() : EntityMapper<ImageModel?, ImageEntity> {

    override fun mapFromRemote(type: ImageModel?): ImageEntity {
        return ImageEntity(
            type?.id ?: "",
            type?.url ?: "",
            type?.mobile_id ?: ""
        )
    }

}