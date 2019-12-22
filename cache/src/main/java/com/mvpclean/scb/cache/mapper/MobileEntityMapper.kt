package com.mvpclean.scb.cache.mapper

import com.mvpclean.scb.cache.model.CachedMobile
import com.mvpclean.scb.data.model.MobileEntity
import javax.inject.Inject

class MobileEntityMapper @Inject constructor() :
    EntityMapper<CachedMobile, MobileEntity> {

    override fun mapToCached(type: MobileEntity): CachedMobile {
        return CachedMobile(
            type.id,
            type.thumbImageURL,
            type.description,
            type.name,
            type.brand,
            type.price,
            type.rating
        )
    }

    override fun mapFromCached(type: CachedMobile): MobileEntity {
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