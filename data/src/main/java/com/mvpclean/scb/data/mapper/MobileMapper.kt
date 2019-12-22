package com.mvpclean.scb.data.mapper

import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.domain.model.Mobile
import javax.inject.Inject


open class MobileMapper @Inject constructor() :
    Mapper<MobileEntity, Mobile> {

    override fun mapFromEntity(type: MobileEntity): Mobile {
        return Mobile(
            type.id,
            type.thumbImageURL,
            type.description,
            type.name,
            type.brand,
            type.price,
            type.rating
        )
    }

    override fun mapToEntity(type: Mobile): MobileEntity {
        return MobileEntity(
            type.id,
            type.thumbImageURL,
            type.description,
            type.name,
            type.brand,
            type.price,
            type.rating
        )
    }

}