package com.example.android_kotlin_tasks.domain.services

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesService (context:Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs",Context.MODE_PRIVATE)
    fun write(key:String,value:String){
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun read(key: String): String? {
        return sharedPreferences.getString(key, "");
    }

}