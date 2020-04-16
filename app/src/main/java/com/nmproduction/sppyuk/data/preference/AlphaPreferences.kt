package com.nmproduction.sppyuk.data.preference

import android.content.Context
import androidx.preference.PreferenceManager


class AlphaPreferences(context: Context) {
    fun clearAll() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val uidModel = "data.sources.preferences.UID"
        private const val userModel = "data.sources.preferences.USER"
        private const val nameModel = "data.sources.preferences.NAME"
        private const val typeModel = "data.sources.preferences.TYPE"
        private const val levelModel = "data.sources.preferences.LEVEL"

    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var uid = preferences.getString(uidModel, "")
        set(value) = preferences.edit().putString(uidModel, value).apply()

    var name = preferences.getString(nameModel, "")
        set(value) = preferences.edit().putString(nameModel, value).apply()

    var tipe = preferences.getString(typeModel, "")
        set(value) = preferences.edit().putString(typeModel, value).apply()

    var level = preferences.getString(levelModel, "")
        set(value) = preferences.edit().putString(levelModel, value).apply()

    var user = preferences.getString(userModel, "")
        set(value) = preferences.edit().putString(userModel, value).apply()

}