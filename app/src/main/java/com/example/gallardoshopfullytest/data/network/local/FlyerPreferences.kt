package com.example.gallardoshopfullytest.data.network.local

import android.content.Context
import android.content.SharedPreferences
import com.example.gallardoshopfullytest.data.model.Flyer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FlyerPreferences(context: Context) {

    private val gson = Gson()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveFlyers(flyers: List<Flyer>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(FLYERS, gson.toJson(flyers))
        editor.apply()
    }

    fun getFlyers(): List<Flyer> {
        val json = sharedPreferences.getString(FLYERS, "")
        val type: Type = object : TypeToken<List<Flyer?>?>() {}.type
        return gson.fromJson<Any>(json, type) as List<Flyer>
    }


    companion object {
        private const val PREFS_NAME = "com.example.gallardoshopfullytest.flyers"
        private const val FLYERS = "FLYERS"
    }
}