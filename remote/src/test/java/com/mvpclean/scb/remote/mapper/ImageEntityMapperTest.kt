package com.mvpclean.scb.remote.mapper

import com.mvpclean.scb.remote.test.factory.Factory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ImageEntityMapperTest {

    private lateinit var entityMpper: ImageEntityMapper

    @Before
    fun setUp() {
        entityMpper = ImageEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        // GIVEN
        val model = Factory.makeImageModel()
        // WHEN
        val entity = entityMpper.mapFromRemote(model)
        // THEN
        assertEquals(model.id, entity.id)
        assertEquals(model.mobile_id, entity.mobile_id)
        assertEquals(model.url, entity.url)
    }

}