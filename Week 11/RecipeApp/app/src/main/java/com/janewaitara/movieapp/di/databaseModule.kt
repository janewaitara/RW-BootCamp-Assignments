package com.janewaitara.movieapp.di

import androidx.room.Room
import com.janewaitara.movieapp.db.DATABASE_NAME
import com.janewaitara.movieapp.db.RecipeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module{
    single { Room.databaseBuilder(androidContext(), RecipeDatabase::class.java, DATABASE_NAME).build() }
    single { get<RecipeDatabase>().recipeDao()}
}