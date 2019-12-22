package com.mvpclean.scb.data.source

import com.mvpclean.scb.data.repository.Cache
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataStoreFactoryTest {

    private lateinit var dataStoreFactory: DataStoreFactory
    @Mock
    private lateinit var cache: Cache
    @Mock
    private lateinit var cacheDataStore: CacheDataStore
    @Mock
    private lateinit var remoteDataStore: RemoteDataStore

    @Before
    fun setUp() {
        dataStoreFactory = DataStoreFactory(
            cache,
            cacheDataStore,
            remoteDataStore
        )
    }

    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        val dataStore = dataStoreFactory.retrieveDataStore()
        assert(dataStore is RemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        val dataStore = dataStoreFactory.retrieveDataStore()
        assert(dataStore is RemoteDataStore)
    }

    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val dataStore = dataStoreFactory.retrieveRemoteDataStore()
        assert(dataStore is RemoteDataStore)
    }

    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val dataStore = dataStoreFactory.retrieveCacheDataStore()
        assert(dataStore is CacheDataStore)
    }


}