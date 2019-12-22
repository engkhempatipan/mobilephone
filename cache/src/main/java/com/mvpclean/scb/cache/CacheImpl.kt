package com.mvpclean.scb.cache

import android.database.sqlite.SQLiteDatabase
import com.mvpclean.scb.cache.db.Db
import com.mvpclean.scb.cache.db.DbOpenHelper
import com.mvpclean.scb.cache.db.constants.Constants
import com.mvpclean.scb.cache.db.mapper.CachedMobilesMapper
import com.mvpclean.scb.cache.mapper.MobileEntityMapper
import com.mvpclean.scb.data.model.MobileEntity
import com.mvpclean.scb.data.repository.Cache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CacheImpl @Inject constructor(
    dbOpenHelper: DbOpenHelper,
    private val preferencesHelper: PreferencesHelper,
    private val mobileEntityMapper: MobileEntityMapper,
    private val mapper: CachedMobilesMapper
) : Cache {

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    override fun getMobiles(): Single<List<MobileEntity>> {
        return Single.defer<List<MobileEntity>> {
            val updatesCursor = database.rawQuery(Constants.QUERY_GET_MOBILE, null)
            val mobiles = mutableListOf<MobileEntity>()
            while (updatesCursor.moveToNext()) {
                val cachedMobile = mapper.parseCursor(updatesCursor)
                mobiles.add(mobileEntityMapper.mapFromCached(cachedMobile))
            }
            updatesCursor.close()
            Single.just<List<MobileEntity>>(mobiles)
        }
    }

    override fun saveMobile(cachedMobile: MobileEntity): Completable {
        return Completable.defer {
            try {
                database.beginTransaction()
                val cached = mobileEntityMapper.mapToCached(cachedMobile)
                database.insert(Db.Table.TABLE_MOBILE, null, mapper.toContentValues((cached)))
                database.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    override fun deleteMobile(id: String): Completable {
        return Completable.defer {
            try {
                database.beginTransaction()
                database.delete(Db.Table.TABLE_MOBILE, Db.Table.ID + "=" + id, null)
                database.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    override fun setSortBy(sortBy: String): Completable {
        return Completable.defer {
            try {
                preferencesHelper.sortBy = sortBy
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Completable.complete()
        }
    }

    override fun getSortBy():Single<String>{
        return Single.just(preferencesHelper.sortBy)
    }

}