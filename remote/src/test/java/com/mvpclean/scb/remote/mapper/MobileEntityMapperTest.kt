package com.mvpclean.scb.remote.mapper

import com.mvpclean.scb.remote.test.factory.Factory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MobileEntityMapperTest {

    private lateinit var entityMpper: MobileEntityMapper

    @Before
    fun setUp() {
        entityMpper = MobileEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        // GIVEN
        val model = Factory.makeMobileModel()
        // WHEN
        val entity = entityMpper.mapFromRemote(model)
        // THEN
        assertEquals(model.id, entity.id)
        assertEquals(model.thumbImageURL, entity.thumbImageURL)
        assertEquals(model.description, entity.description)
        assertEquals(model.name, entity.name)
        assertEquals(model.brand, entity.brand)
        assertEquals(model.price, entity.price)
        assertEquals(model.rating, entity.rating)
    }

}