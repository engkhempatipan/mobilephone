package com.mvpclean.scb.data.source

import com.mvpclean.scb.data.repository.Remote
import com.mvpclean.scb.data.test.factory.DataFactory
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeListImageEntity
import com.mvpclean.scb.data.test.factory.Factory.Factory.makeListMobileEntity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataStoreTest {

    private lateinit var remoteDataStore: RemoteDataStore
    @Mock
    private lateinit var remote: Remote

    @Before
    fun setUp() {
        remoteDataStore = RemoteDataStore(remote)
    }


    @Test
    fun getMobiles() {
        // GIVEN
        whenever(remote.getMobiles()).thenReturn(Single.just(makeListMobileEntity(10)))
        // WHEN
        val testObserver = remote.getMobiles().test()
        // THEN
        testObserver.assertComplete()
    }

    @Test
    fun getImagesByMobileId() {
        // GIVEN
        val id = DataFactory.randomUuid()
        whenever(remote.getImagesByMobileId(id)).thenReturn(Single.just(makeListImageEntity(10)))
        // WHEN
        val testObserver = remote.getImagesByMobileId(id).test()
        // THEN
        testObserver.assertComplete()
    }

}