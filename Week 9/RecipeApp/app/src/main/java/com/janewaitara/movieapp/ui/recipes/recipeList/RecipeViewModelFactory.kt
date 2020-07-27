package com.janewaitara.movieapp.ui.recipes.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janewaitara.movieapp.repository.RoomRepository
import com.janewaitara.movieapp.ui.recipes.RecipeViewModel

class RecipeViewModelFactory(
    private val repository: RoomRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(repository) as T
    }
}