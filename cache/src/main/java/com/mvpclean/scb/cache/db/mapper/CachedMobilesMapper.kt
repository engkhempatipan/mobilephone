package com.mvpclean.scb.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import com.mvpclean.scb.cache.db.Db
import com.mvpclean.scb.cache.model.CachedMobile
import javax.inject.Inject

class CachedMobilesMapper @Inject constructor() :
    ModelDbMapper<CachedMobile> {

    override fun toContentValues(model: CachedMobile): ContentValues {
        val values = ContentValues()
        //values.put(Db.Table.JSON_MOBILES_CACHED, Gson().toJson(model))
        values.put(Db.Table.ID, model.id)
        values.put(Db.Table.THUMBNAIL, model.thumbImageURL)
        values.put(Db.Table.DESCRIPTION, model.description)
        values.put(Db.Table.NAME, model.name)
        values.put(Db.Table.BRAND, model.brand)
        values.put(Db.Table.PRICE, model.price)
        values.put(Db.Table.RATING, model.rating)
        return values
    }

    override fun parseCursor(cursor: Cursor): CachedMobile {
//        val jsonString = cursor.getString(
//            cursor.getColumnIndexOrThrow(Db.Table.ID)
//        )
        val id = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.ID))
        val thumbnail = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.THUMBNAIL))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.DESCRIPTION))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.NAME))
        val brand = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.BRAND))
        val price = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.PRICE))
        val ratting = cursor.getString(cursor.getColumnIndexOrThrow(Db.Table.RATING))
//        return Gson().fromJson(jsonString, CachedMobiles::class.java)
        return CachedMobile(
            id,
            thumbnail,
            description,
            name,
            brand,
            price,
            ratting
        )
    }

}