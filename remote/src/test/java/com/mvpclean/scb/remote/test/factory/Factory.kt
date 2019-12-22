package com.mvpclean.scb.remote.test.factory

import com.mvpclean.scb.remote.model.ImageModel
import com.mvpclean.scb.remote.model.MobileModel
import com.mvpclean.scb.remote.test.factory.DataFactory.Factory.randomUuid

class Factory {

    companion object Factory {

        fun makeImageModel(): ImageModel {
            return ImageModel(
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeListImageModel(count: Int): List<ImageModel>? {
            val list = mutableListOf<ImageModel>()
            repeat(count) {
                list.add(makeImageModel())
            }
            return list
        }

        fun makeMobileModel(): MobileModel {
            return MobileModel(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid()
            )
        }

        fun makeListMobileModel(count: Int): List<MobileModel>? {
            val list = mutableListOf<MobileModel>()
            repeat(count) {
                list.add(makeMobileModel())
            }
            return list
        }

    }

}