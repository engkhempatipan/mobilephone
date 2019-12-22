package com.mvpclean.scb.cache.test.factory

import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.cache.test.factory.DataFactory.Factory.randomUuid

class Factory {

    companion object Factory {

        fun makeMobileEntity():MobileEntity{
            return MobileEntity(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }
    }

}