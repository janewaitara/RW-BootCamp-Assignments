package com.janewaitara.movieapp.di

import com.janewaitara.movieapp.ui.recipes.RecipeViewModel
import com.janewaitara.movieapp.ui.recipes.searchRecipeInformation.SearchRecipeInformationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { RecipeViewModel(get(),get()) }
    viewModel { SearchRecipeInformationViewModel(get()) }
}