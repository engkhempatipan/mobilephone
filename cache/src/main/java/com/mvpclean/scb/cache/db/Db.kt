package com.mvpclean.scb.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object Table {

        const val TABLE_MOBILE = "mobiles"

        const val ID = "my_id"
        const val THUMBNAIL = "thumbnail"
        const val DESCRIPTION = "description"
        const val NAME = "name"
        const val BRAND = "brand"
        const val PRICE = "price"
        const val RATING = "rating"

        const val JSON_MOBILES_CACHED = "jsoncached"

        const val CREATE =
            "CREATE TABLE " + TABLE_MOBILE + " (" +
                    ID + " TEXT PRIMARY KEY NOT NULL," +
                    THUMBNAIL + " TEXT," +
                    DESCRIPTION + " TEXT," +
                    NAME + " TEXT," +
                    BRAND + " TEXT," +
                    PRICE + " TEXT," +
                    RATING + " TEXT" +
                    "); "
    }

}