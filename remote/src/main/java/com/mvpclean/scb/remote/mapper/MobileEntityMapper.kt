package com.mvpclean.scb.remote.mapper

import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.remote.model.MobileModel
import javax.inject.Inject

open class MobileEntityMapper @Inject constructor() : EntityMapper<MobileModel?, MobileEntity> {

    override fun mapFromRemote(type: MobileModel?): MobileEntity {
        return MobileEntity(
            type?.id ?: "",
            type?.thumbImageURL ?: "",
            type?.description ?: "",
            type?.name ?: "",
            type?.brand ?: "",
            type?.price ?: "",
            type?.rating ?: ""
        )
    }

}