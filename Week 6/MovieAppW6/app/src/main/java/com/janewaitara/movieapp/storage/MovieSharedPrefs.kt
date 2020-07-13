package com.janewaitara.movieapp.storage

import android.content.Context
import com.janewaitara.movieapp.MovieApplication

class MovieSharedPrefs {

    private val SHARED_PREFS = "SHARED_PREFS"
    val ISLOGGED_IN = "IS_LOGGED_IN "
    private val context =
        MovieApplication.getAppContext()

    var sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun setLoginStatus(isLoggedIn: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean(ISLOGGED_IN,isLoggedIn)
        editor.apply()
    }

    fun getLoginStatus(): Boolean = sharedPreferences.getBoolean(ISLOGGED_IN,false)

}