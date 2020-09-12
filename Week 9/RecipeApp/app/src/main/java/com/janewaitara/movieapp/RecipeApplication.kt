package com.janewaitara.movieapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.janewaitara.movieapp.db.DATABASE_NAME
import com.janewaitara.movieapp.db.RecipeDao
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.networking.RemoteApi
import com.janewaitara.movieapp.networking.buildApiService
import com.janewaitara.movieapp.repository.RoomRepository
import com.janewaitara.movieapp.ui.recipes.recipeList.RecipeViewModelFactory
import com.janewaitara.movieapp.ui.recipes.searchRecipeInformation.SearchRecipeInformationViewModelFactory

class RecipeApplication:Application() {
    companion object{

        private lateinit var instance : RecipeApplication

        private val recipeDatabase: RecipeDatabase by lazy {
            Room.databaseBuilder(
                instance,
                RecipeDatabase::class.java, DATABASE_NAME).build()
        }

        fun getAppContext(): Context = instance.applicationContext


       // private val recipeDao: RecipeDao = RecipeDatabase.getDatabase().recipeDao()
        private val recipeDao by lazy { recipeDatabase.recipeDao() }
        //exposing the remoteAPI to the rest of the app to the rest of the app(building and passing)
        private val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }

        val repository: RoomRepository by lazy { RoomRepository(recipeDao, remoteApi)}

        val recipeViewModelFactory by lazy { RecipeViewModelFactory(repository, remoteApi) }

        val searchRecipeInfoViewModelFactory by lazy{
            SearchRecipeInformationViewModelFactory(remoteApi) }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

    }
}
