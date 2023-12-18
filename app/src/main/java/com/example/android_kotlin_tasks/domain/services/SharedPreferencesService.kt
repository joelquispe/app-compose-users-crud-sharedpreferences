package com.example.android_kotlin_tasks.domain.services

import android.content.Context

class SharedPreferencesService (context:Context){
    val sharedPreferences = context.getSharedPreferences("prefs",Context.MODE_PRIVATE)
    fun write(key:String,value:String){
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun read(key:String):String?{
        val data = sharedPreferences.getString(key,"")
        return data;
    }

}