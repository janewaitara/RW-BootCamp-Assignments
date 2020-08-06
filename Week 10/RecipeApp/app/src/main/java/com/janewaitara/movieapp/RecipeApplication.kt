package com.janewaitara.movieapp

import android.app.Application
import android.content.Context
import com.janewaitara.movieapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RecipeApplication:Application() {
    companion object{

        private lateinit var instance : RecipeApplication

        fun getAppContext(): Context = instance.applicationContext
/*
        private val recipeDatabase: RecipeDatabase by lazy {
            Room.databaseBuilder(
                instance,
                RecipeDatabase::class.java, DATABASE_NAME).build()
        }

       // private val recipeDao: RecipeDao = RecipeDatabase.getDatabase().recipeDao()
        private val recipeDao by lazy { recipeDatabase.recipeDao() }
        //exposing the remoteAPI to the rest of the app to the rest of the app(building and passing)
        private val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }

        val repository: RoomRepository by lazy { RoomRepository(recipeDao, remoteApi)}

        val recipeViewModelFactory by lazy { RecipeViewModelFactory(repository, remoteApi) }

        val searchRecipeInfoViewModelFactory by lazy{
            SearchRecipeInformationViewModelFactory(remoteApi) }
   */
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        startingKoin()
    }

    private fun startingKoin(){
       startKoin {
           androidLogger(Level.DEBUG)
           androidContext(this@RecipeApplication)
           modules(
               listOf(
                   databaseModule,
                   repositoryModule,
                   networkModule,
                   presentationModule,
                   remoteApiModule
               )
           )
       }
    }
}
