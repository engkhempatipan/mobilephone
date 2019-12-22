package com.mvpclean.scb.data.source

import com.mvpclean.scb.data.model.ImageEntity
import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.data.repository.DataStore
import com.mvpclean.scb.data.repository.Remote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class RemoteDataStore @Inject constructor(
    private val remote: Remote
) : DataStore {

    override fun getMobiles(): Single<List<MobileEntity>> {
        return remote.getMobiles()
    }

    override fun getImagesByMobileId(id: String): Single<List<ImageEntity>> {
        return remote.getImagesByMobileId(id)
    }

    override fun saveMobile(mobile: MobileEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun deleteMobile(id: String): Completable {
        throw UnsupportedOperationException()
    }

    override fun setSortBy(key: String): Completable {
        throw UnsupportedOperationException()
    }

    override fun getSortBy(): Single<String> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteList(): Single<List<MobileEntity>> {
        throw UnsupportedOperationException()
    }

}