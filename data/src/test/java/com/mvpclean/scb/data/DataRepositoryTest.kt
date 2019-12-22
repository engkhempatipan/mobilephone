package com.mvpclean.scb.data

import com.mvpclean.scb.data.mapper.ImageMapper
import com.mvpclean.scb.data.mapper.MobileMapper
import com.mvpclean.scb.data.source.CacheDataStore
import com.mvpclean.scb.data.source.DataStoreFactory
import com.mvpclean.scb.data.source.RemoteDataStore
import com.mvpclean.scb.data.test.factory.DataFactory.Factory.randomUuid
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeListImageEntity
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeListMobileEntity
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeMobile
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeMobileEntity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest {

    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var dataStoreFactory: DataStoreFactory
    @Mock
    private lateinit var cacheDataStore: CacheDataStore
    @Mock
    private lateinit var remoteDataStore: RemoteDataStore
    @Mock
    private lateinit var mobileMapper: MobileMapper
    @Mock
    private lateinit var imageMapper: ImageMapper

    @Before
    fun setUp() {
        dataRepository = DataRepository(
            dataStoreFactory,
            mobileMapper,
            imageMapper
        )
        stubDataStoreFactoryRetrieveRemoteDataStore()
        stubDataStoreFactoryRetrieveCachedDataStore()
    }

    private fun stubDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(dataStoreFactory.retrieveRemoteDataStore())
            .thenReturn(remoteDataStore)
    }

    private fun stubDataStoreFactoryRetrieveCachedDataStore(){
        whenever(dataStoreFactory.retrieveCacheDataStore())
            .thenReturn(cacheDataStore)
    }


    @Test
    fun getMobilesComplete() {
        val mobiles = makeListMobileEntity(10)
        whenever(remoteDataStore.getMobiles()).thenReturn(Single.just(mobiles))

        val testObserver = dataRepository.getMobiles().test()
        testObserver.assertComplete()
    }

    @Test
    fun getImagesByMobileIdComplete() {
        val id = randomUuid()
        val images = makeListImageEntity(10)
        whenever(remoteDataStore.getImagesByMobileId(id)).thenReturn(Single.just(images))

        val testObserver = dataRepository.getImagesByMobileId(id).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMobile(){
        val mobile = makeMobile()
        val mobileEntity = makeMobileEntity()
        whenever(cacheDataStore.saveMobile(any())).thenReturn(Completable.complete())
        whenever(mobileMapper.mapToEntity(mobile)).thenReturn(mobileEntity)

        val testObserver = dataRepository.saveMobile(mobile).test()
        testObserver.assertComplete()
    }

    @Test
    fun deleteMobile(){
        val id = randomUuid()
        whenever(cacheDataStore.deleteMobile(any())).thenReturn(Completable.complete())

        val testObserver = dataRepository.deleteMobile(id).test()
        testObserver.assertComplete()
    }

    @Test
    fun getFavoriteList(){
        val mobiles = makeListMobileEntity(10)
        whenever(cacheDataStore.getFavoriteList()).thenReturn(Single.just(mobiles))

        val testObserver = dataRepository.getFavoriteList().test()
        testObserver.assertComplete()
    }

    @Test
    fun setSortBy(){
        val key = randomUuid()
        whenever(cacheDataStore.setSortBy(key)).thenReturn(Completable.complete())

        val testObserver = dataRepository.setSortBy(key).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSortBy(){
        val key = randomUuid()
        whenever(cacheDataStore.getSortBy()).thenReturn(Single.just(key))

        val testObserver = dataRepository.getSortBy().test()
        testObserver.assertComplete()
    }


}