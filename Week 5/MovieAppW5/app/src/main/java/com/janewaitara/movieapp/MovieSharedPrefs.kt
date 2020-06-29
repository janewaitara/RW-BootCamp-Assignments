package com.janewaitara.movieapp

import android.content.Context
import android.content.SharedPreferences

class MovieSharedPrefs(context: Context) {

    private val SHARED_PREFS = "SHARED_PREFS"
    val ISLOGGED_IN = "IS_LOGGED_IN "

    var sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun setLoginStatus(isLoggedIn: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean(ISLOGGED_IN,isLoggedIn)
        editor.apply()
    }

    fun getLoginStatus(): Boolean = sharedPreferences.getBoolean(ISLOGGED_IN,false)

}