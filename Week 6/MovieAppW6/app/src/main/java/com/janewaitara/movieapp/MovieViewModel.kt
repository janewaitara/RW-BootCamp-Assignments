package com.janewaitara.movieapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RoomRepository

    /**Using LiveData has several benefits:
    We can put an observer on the data (instead of polling for changes) and only update the
    the UI when the data actually changes.
    Repository is completely separated from the UI through the ViewModel.
     */

    //LiveData member variable to cache the list of words.
    val allMovies: LiveData<List<Movie>>

    init {
        repository =  RoomRepository()
        allMovies = repository.getAllMovies()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.addMovie(movie)
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }

    fun insertMovies(movieList: List<Movie>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllMovies(movieList)
        }
    }

    fun getMovie(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovie(id)
        }
    }


}