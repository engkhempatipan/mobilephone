package com.mvpclean.scb.cache

import com.mvpclean.scb.cache.db.Db
import com.mvpclean.scb.cache.db.DbOpenHelper
import com.mvpclean.scb.cache.db.mapper.CachedMobilesMapper
import com.mvpclean.scb.cache.mapper.MobileEntityMapper
import com.mvpclean.scb.cache.test.factory.Factory.Factory.makeMobileEntity
import com.mvpclean.scb.data.model.MobileEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class CacheImplTest {

    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)
    private var mobileEntityMapper = MobileEntityMapper()
    private var mapper = CachedMobilesMapper()
    private var databaseHelper = CacheImpl(
        DbOpenHelper(RuntimeEnvironment.application),
        preferencesHelper,
        mobileEntityMapper,
        mapper
    )

    @Before
    fun setUp() {
        databaseHelper.getDatabase().rawQuery("DELETE FROM " + Db.Table.TABLE_MOBILE, null)
    }


    @Test
    fun getMobilesComplete() {
        val mobileEntity = makeMobileEntity()
        insertMobile(mobileEntity)
        val testObserver = databaseHelper.getMobiles().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMobile() {
        val count = 1
        val mobileEntity = makeMobileEntity()
        databaseHelper.saveMobile(mobileEntity).test()
        checkNumrow(count)
    }

    private fun insertMobile(cachedMobile: MobileEntity) {
        val cached = mobileEntityMapper.mapToCached(cachedMobile)
        val database = databaseHelper.getDatabase()
        database.insertOrThrow(Db.Table.TABLE_MOBILE, null, mapper.toContentValues(cached))
    }


    private fun checkNumrow(expectedRows: Int) {
        val cursor = databaseHelper.getDatabase().query(
            Db.Table.TABLE_MOBILE,
            null, null, null, null, null, null
        )
        cursor.moveToFirst()
        val numberOfRows = cursor.count
        cursor.close()
        assertEquals(expectedRows, numberOfRows)
    }
}