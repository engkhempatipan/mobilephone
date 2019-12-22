package com.mvpclean.scb.data.source

import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.data.repository.Cache
import com.mvpclean.scb.data.test.factory.DataFactory.Factory.randomUuid
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeListMobileEntity
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeMobileEntity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CacheDataStoreTest {

    private lateinit var cacheDataStore: CacheDataStore
    @Mock
    private lateinit var cache: Cache

    @Before
    fun setUp() {
        cacheDataStore = CacheDataStore(cache)
    }

    @Test
    fun getMobiles() {
        stubGetCachedMobile(Single.just(makeListMobileEntity(10)))
        val testObserver = cacheDataStore.getMobiles().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveFavorite(){
        val mobileEntity = makeMobileEntity()
        whenever(cache.saveMobile(mobileEntity)).thenReturn(Completable.complete())

        val testObserver = cacheDataStore.saveMobile(mobileEntity).test()
        testObserver.assertComplete()
    }

    @Test
    fun deleteFavorite(){
        val id = randomUuid()
        whenever(cache.deleteMobile(id)).thenReturn(Completable.complete())

        val testObserver = cacheDataStore.deleteMobile(id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setSortBy(){
        val key = randomUuid()
        whenever(cache.setSortBy(key)).thenReturn(Completable.complete())

        val testObserver = cacheDataStore.setSortBy(key).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSortBy(){
        val key = randomUuid()
        whenever(cache.getSortBy()).thenReturn(Single.just(key))

        val testObserver = cacheDataStore.getSortBy().test()
        testObserver.assertComplete()
    }

    private fun stubGetCachedMobile(single: Single<List<MobileEntity>>){
        whenever(cache.getMobiles()).thenReturn(single)
    }
}