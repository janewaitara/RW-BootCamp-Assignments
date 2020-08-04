package com.janewaitara.movieapp.ui.recipes.searchRecipeInformation

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.model.Ingredient
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.model.response.SearchRecipeInformationResponse
import com.janewaitara.movieapp.model.response.SearchRecipeIngredient
import com.janewaitara.movieapp.networking.NetworkStatusChecker
import com.janewaitara.movieapp.ui.recipes.IngredientsAdapter
import com.janewaitara.movieapp.ui.recipes.RecipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_search_recipe_information.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchRecipeInformation : Fragment() {
    private val searchRecipeInfoViewModel by lazy {
        ViewModelProvider(this, RecipeApplication.searchRecipeInfoViewModelFactory)
            .get(SearchRecipeInformationViewModel::class.java)
    }

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private lateinit var ingredientsRecyclerView: RecyclerView


    private var recipeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_recipe_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = SearchRecipeInformationArgs.fromBundle(it)
            recipeId = args.recipeId

            Log.d("Recipe Id", recipeId.toString())

            getRecipeInformationFromId(view, recipeId!!)
        }
    }

    private fun getRecipeInformationFromId( view: View,recipeId: Int){
        networkStatusChecker.performIfConnectedToInternet {
            lifecycleScope.launch (Dispatchers.Main){

                searchRecipeInfoViewModel.searchRecipeFromApiUsingSearchParameter(recipeId)

                searchRecipeInfoViewModel.getSearchedInfoRecipeLiveData().observe(viewLifecycleOwner, Observer { searchRecipeInfoResponse ->
                    /**
                     * Wrapped within let expression since the response can be null*/
                    searchRecipeInfoResponse?.let { searchRecipeInfo->
                        bindResultsWithViews(view, searchRecipeInfo)
                    }
                })

            }
        }
    }

    private fun bindResultsWithViews( view: View, recipeData: SearchRecipeInformationResponse) {
        Picasso.get().load(recipeData.image).into(search_recipe_image)
        search_recipe_title.text = recipeData.title
        search_recipe_duration.text = recipeData.readyInMinutes.toString()
        ingredientsBuilder(recipeData)

        Log.d("Recipe instructions", recipeData.instructions ?: "")

        when(recipeData.instructions){
            null -> search_recipe_instructions.text  = "Unfortunately no instructions found"
            else -> instructionBuilder(recipeData)
        }
        /*if (recipeData.instructions != null){
            instructionBuilder(recipeData)
        }else{
            search_recipe_instructions.text  = "Unfortunately no instructions found"
        }*/



        search_recipe_summary.text = recipeData.summary

        populateIngredientRecyclerView(view, recipeData.extendedIngredients)

    }

    private fun populateIngredientRecyclerView(
        view: View,
        extendedIngredients: List<Ingredient>) {
        ingredientsRecyclerView = view.findViewById(R.id.search_recipeRecyclerView)
        ingredientsRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = IngredientsAdapter(extendedIngredients)
        ingredientsRecyclerView.adapter = adapter

        Log.d("IMAGE", "Image: ${extendedIngredients[1].image}")

    }

    private fun ingredientsBuilder(recipeData: SearchRecipeInformationResponse){
        val ingredients = StringBuilder()
        val ingredientList = recipeData.extendedIngredients

        ingredientList.forEach{ ingredientItem->

            ingredients.append('\u2666'.toString()).append("  ").append(ingredientItem.originalString).append("\n")
        }
        search_recipe_ingredients.text = ingredients
    }

    private fun instructionBuilder(recipeData: SearchRecipeInformationResponse){

        val instructionsBuilder = StringBuilder()
        val recipeInstructions = recipeData.instructions
        val instructions = recipeInstructions?.split(".")
        val filteredInstructions = instructions?.filterNot { it == "<p>"|| it == "</p"|| it == "</li>" || it == "<li>"|| it == "<ol>" }
        filteredInstructions?.forEach { instruction->
            instructionsBuilder.append('\u2666'.toString()).append("  ").append(instruction).append("\n")
        }
        search_recipe_instructions.text = instructionsBuilder
    }


}