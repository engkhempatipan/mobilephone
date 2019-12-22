package com.mvpclean.scb.cache

import android.content.Context
import android.content.SharedPreferences
import com.mvpclean.scb.BuildConfig
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(REF_PACKAGE_NAEM, Context.MODE_PRIVATE)
    }

    var sortBy: String
        get() = sharedPreferences.getString(PREF_SORT_KEY_CACHED, SORT_BY_PRICE_LOW_TO_HIGH)
        set(sortBy) = sharedPreferences.edit().putString(PREF_SORT_KEY_CACHED, sortBy).apply()

    companion object {
        private val REF_PACKAGE_NAEM = BuildConfig.LIBRARY_PACKAGE_NAME
        private val PREF_SORT_KEY_CACHED = "PREF_SORT_KEY_CACHED"
        val SORT_BY_PRICE_HIGH_TO_LOW = "SORT_BY_PRICE_HIGH_TO_LOW"
        val SORT_BY_PRICE_LOW_TO_HIGH = "SORT_BY_PRICE_LOW_TO_HIGH"
        val SORT_BY_RATING = "SORT_BY_RATING"
    }
}
