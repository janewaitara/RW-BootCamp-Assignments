package com.janewaitara.movieapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.networking.RemoteApi
import com.janewaitara.movieapp.networking.buildApiService

class RecipeApplication:Application() {
    companion object{

        private lateinit var instance : RecipeApplication

        lateinit var database: RecipeDatabase

        fun getAppContext(): Context = instance.applicationContext

        //exposing the remoteAPI to the rest of the app to the rest of the app(building and passing)
        private val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        database = Room.databaseBuilder(this,
            RecipeDatabase::class.java,"movie_database").build()
    }
}