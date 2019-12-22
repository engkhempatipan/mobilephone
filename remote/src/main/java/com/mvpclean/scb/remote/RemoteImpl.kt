package com.mvpclean.scb.remote

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.data.repository.Remote
import com.mvpclean.scb.remote.mapper.ImageEntityMapper
import com.mvpclean.scb.remote.mapper.MobileEntityMapper
import io.reactivex.Single
import javax.inject.Inject

class RemoteImpl @Inject constructor(
    private val service: Service,
    private val mobileEntityMapper: MobileEntityMapper,
    private val imageEntityMapper: ImageEntityMapper
) : Remote {

    override fun getMobiles(): Single<List<MobileEntity>> {
        return service.getMobiles().map {
            it.map { mobileModel ->
                mobileEntityMapper.mapFromRemote(mobileModel)
            }
        }
    }

    override fun getImagesByMobileId(id: String): Single<List<ImageEntity>> {
        return service.getImagesByMobileId(id).map {
            it.map { imageModel ->
                imageEntityMapper.mapFromRemote(imageModel)
            }
        }
    }

}