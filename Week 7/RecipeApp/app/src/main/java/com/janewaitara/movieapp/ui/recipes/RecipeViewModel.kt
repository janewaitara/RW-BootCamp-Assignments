package com.janewaitara.movieapp.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Ingredient
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        var recipeList = mutableListOf(
            Recipe(
                94,
                "The Grudge",
                "A house is cursed by a vengeful ghost that dooms those who enter it with a violent death.",
                10,
                R.drawable.thegrudge,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                                Ingredient("Pork"),
                                Ingredient("Pork") )
            ),
            Recipe(
                100,
                "The Call of the Wild ",
                "A sled dog struggles for survival in the wilds of the Yukon.",
                20,
                R.drawable.thecallofwind,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ), Recipe(
                97,
                "A Quiet Place Part II ",
                "Following the events at home, the Abbott family now face the terrors of the outside world. " +
                        "Forced to venture into the unknown, they realize the creatures that hunt by sound are not the only threats lurking beyond the sand path.",
                25,
                R.drawable.quiet_place,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                95,
                "Underwater",
                "A crew of oceanic researchers working for a deep sea drilling company" +
                        " try to get to safety after a mysterious earthquake devastates their deepwater research " +
                        "and drilling facility located at the bottom of the Mariana Trench.",
                20,
                R.drawable.under_water,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                83,
                "Like a Boss",
                "Two friends with very different ideals start a beauty company together. O" +
                        "ne is more practical while the other wants to earn her fortune and live a lavish lifestyle.",
                26,
                R.drawable.like_boss,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                124,
                "Bad Boys for life",
                "Miami detectives Mike Lowrey and Marcus Burnett must face off against a " +
                        "mother-and-son pair of drug lords who wreak vengeful havoc on their city.",
                25,
                R.drawable.bad_boys_for_life,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                101,
                "Dolitle",
                "A physician who can talk to animals embarks on an adventure to find a " +
                        "legendary island with a young apprentice and a crew of strange pets.",
                30,
                R.drawable.dolitte,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                113,
                "The Gentlemen",
                "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.",
                35,
                R.drawable.gentle_men,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                94,
                "The turning",
                "A young governess is hired by a man who has become responsible for his young nephew and niece after their parents' deaths. A modern take on Henry James' novella \"The Turn of the Screw.\"",
                30,
                R.drawable.turning,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                109,
                "Rhythm Section",
                "A woman seeks revenge against those who orchestrated a plane crash that killed her family.",
                40,
                R.drawable.rhythm,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                109,
                "Birds of Prey",
                "After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                50,
                R.drawable.birds_of_prey,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                99,
                "Sonic the Hedgehog",
                "After discovering a small, blue, fast hedgehog, a small-town police officer must help him defeat an evil genius who wants to do experiments on him.",
                68,
                R.drawable.hedgehog,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
            ),
            Recipe(
                109,
                "Fantasy island",
                "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
                58,
                R.drawable.fantasy_island,
                "Boil twice",
                mutableListOf(Ingredient("Pork"),
                    Ingredient("Pork"),
                    Ingredient("Pork") )
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
    val allRecipes: LiveData<List<Recipe>>

    init {
        repository = RoomRepository()
        allRecipes = repository.getAllRecipes()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.addRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }

    fun insertRecipes(recipeList: List<Recipe>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllRecipes(recipeList)
        }
    }

    fun getRecipe(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecipe(id)
        }
    }


}