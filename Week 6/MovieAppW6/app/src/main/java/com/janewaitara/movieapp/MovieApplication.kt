package com.janewaitara.movieapp

import android.app.Application
import android.content.Context
import androidx.room.Room

class MovieApplication:Application() {
    companion object{

        private lateinit var instance : MovieApplication

        lateinit var database: MovieDatabase

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        database = Room.databaseBuilder(this,MovieDatabase::class.java,"movie_database").build()
    }
}