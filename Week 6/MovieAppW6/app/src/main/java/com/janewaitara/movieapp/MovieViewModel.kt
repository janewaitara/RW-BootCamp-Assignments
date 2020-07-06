package com.janewaitara.movieapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        var movieList = mutableListOf(
            Movie(
                94,
                "The Grudge",
                "A house is cursed by a vengeful ghost that dooms those who enter it with a violent death.",
                "January 3rd 2020",
                R.drawable.thegrudge
            ),
            Movie(100,
                "The Call of the Wild ",
                "A sled dog struggles for survival in the wilds of the Yukon.",
                "February 21st 2020",
                R.drawable.thecallofwind

            ), Movie(97,
                "A Quiet Place Part II ",
                "Following the events at home, the Abbott family now face the terrors of the outside world. " +
                        "Forced to venture into the unknown, they realize the creatures that hunt by sound are not the only threats lurking beyond the sand path.",
                "March 20th 2020",
                R.drawable.quiet_place
            ),
            Movie(
                95,
                "Underwater",
                "A crew of oceanic researchers working for a deep sea drilling company" +
                        " try to get to safety after a mysterious earthquake devastates their deepwater research " +
                        "and drilling facility located at the bottom of the Mariana Trench.",
                "January 10th 2020",
                R.drawable.under_water
            ),
            Movie(
                83,
                "Like a Boss",
                "Two friends with very different ideals start a beauty company together. O" +
                        "ne is more practical while the other wants to earn her fortune and live a lavish lifestyle.",
                "January 10th 2020",
                R.drawable.like_boss
            ),
            Movie(
                124,
                "Bad Boys for life",
                "Miami detectives Mike Lowrey and Marcus Burnett must face off against a " +
                        "mother-and-son pair of drug lords who wreak vengeful havoc on their city.",
                "January 17th 2020",
                R.drawable.bad_boys_for_life
            ),
            Movie(
                101,
                "Dolitle",
                "A physician who can talk to animals embarks on an adventure to find a " +
                        "legendary island with a young apprentice and a crew of strange pets.",
                "January 17th 2020",
                R.drawable.dolitte
            ),
            Movie(
                113,
                "The Gentlemen",
                "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.",
                "January 24th 2020",
                R.drawable.gentle_men
            ),
            Movie(
                94,
                "The turning",
                "A young governess is hired by a man who has become responsible for his young nephew and niece after their parents' deaths. A modern take on Henry James' novella \"The Turn of the Screw.\"",
                "January 24th 2020",
                R.drawable.turning
            ),
            Movie(
                109,
                "Rhythm Section",
                "A woman seeks revenge against those who orchestrated a plane crash that killed her family.",
                "January 31st 2020",
                R.drawable.rhythm
            ),
            Movie(
                109,
                "Birds of Prey",
                "After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                "February 7th 2020",
                R.drawable.birds_of_prey
            ),
            Movie(
                99,
                "Sonic the Hedgehog",
                "After discovering a small, blue, fast hedgehog, a small-town police officer must help him defeat an evil genius who wants to do experiments on him.",
                "February 14th 2020",
                R.drawable.hedgehog
            ),
            Movie(
                109,
                "Fantasy island",
                "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
                "February 14th 2020",
                R.drawable.fantasy_island
            )
        )
    }

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