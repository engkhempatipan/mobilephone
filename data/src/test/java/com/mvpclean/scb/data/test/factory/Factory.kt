package com.mvpclean.scb.data.test.factory

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.data.test.factory.DataFactory.Factory.randomUuid
import com.mvpclean.scb.domain.model.Image
import com.mvpclean.scb.domain.model.Mobile

class Factory {

    companion object Factory {

        fun makeImageEntity(): ImageEntity {
            return ImageEntity(
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeListImageEntity(count: Int): List<ImageEntity> {
            val list = mutableListOf<ImageEntity>()
            repeat(count) {
                list.add(
                    makeImageEntity()
                )
            }
            return list
        }

        fun makImage(): Image {
            return Image(
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeMobileEntity(): MobileEntity {
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

        fun makeListMobileEntity(count: Int): List<MobileEntity> {
            val list = mutableListOf<MobileEntity>()
            repeat(count) {
                list.add(makeMobileEntity())
            }
            return list
        }

        fun makeMobile(): Mobile {
            return Mobile(
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