package com.janewaitara.movieapp.ui.recipes.searchRecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.ui.recipes.RecipeAdapter


class SearchRecipeFragment : Fragment() ,SearchRecipeAdapter.SearchRecipeListClickListener{

    private lateinit var searchRecipeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val searchParameter =
                SearchRecipeFragmentArgs.fromBundle(it).recipeList.toList()


            searchRecipeRecyclerView = view.findViewById(R.id.searchRecipeRecyclerView)
            searchRecipeRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            val adapter = SearchRecipeAdapter(this)
            searchRecipeRecyclerView.adapter = adapter


            adapter.setSearchRecipes(searchParameter)

        }
    }

    override fun searchRecipeItemClicked(recipe: SearchRecipe) {
        showRecipeInformation(recipe)
    }

    private fun showRecipeInformation(recipe: SearchRecipe) {
        view?.let {
            val action = SearchRecipeFragmentDirections.actionSearchRecipeFragmentToSearchRecipeInformation(recipe.id)

            it.findNavController().navigate(action)
        }
    }

}