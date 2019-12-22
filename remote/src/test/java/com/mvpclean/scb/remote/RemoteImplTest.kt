package com.mvpclean.scb.remote

import com.mvpclean.scb.remote.mapper.ImageEntityMapper
import com.mvpclean.scb.remote.mapper.MobileEntityMapper
import com.mvpclean.scb.remote.model.ImageModel
import com.mvpclean.scb.remote.model.MobileModel
import com.mvpclean.scb.remote.test.factory.DataFactory.Factory.randomUuid
import com.mvpclean.scb.remote.test.factory.Factory.Factory.makeListImageModel
import com.mvpclean.scb.remote.test.factory.Factory.Factory.makeListMobileModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteImplTest {

    private lateinit var remoteImpl: RemoteImpl
    @Mock
    private lateinit var service: Service
    @Mock
    private lateinit var mobileEntityMapper: MobileEntityMapper
    @Mock
    private lateinit var imageEntityMapper: ImageEntityMapper

    @Before
    fun setup() {
        remoteImpl = RemoteImpl(
            service,
            mobileEntityMapper,
            imageEntityMapper
        )
    }

    @Test
    fun getMobiles() {
        val mobiles = makeListMobileModel(10)
        stubServiceGetMobiles(Single.just(mobiles))
        val testObserver = remoteImpl.getMobiles().test()
        testObserver.assertComplete()
    }

    @Test
    fun getImagesByMobileId() {
        val id = randomUuid()
        stubServiceGetImagesByMobileId(id, Single.just(makeListImageModel(10)))
        val testObserver = remoteImpl.getImagesByMobileId(id).test()
        testObserver.assertComplete()
    }

    private fun stubServiceGetMobiles(single: Single<List<MobileModel>?>) {
        whenever(service.getMobiles()).thenReturn(single)
    }

    private fun stubServiceGetImagesByMobileId(id: String, single: Single<List<ImageModel>?>) {
        whenever(service.getImagesByMobileId(id)).thenReturn(single)
    }
}