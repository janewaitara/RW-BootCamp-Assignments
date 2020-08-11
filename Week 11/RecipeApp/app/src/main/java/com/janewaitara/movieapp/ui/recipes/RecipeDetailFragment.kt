package com.janewaitara.movieapp.ui.recipes


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.ChangeBounds
import androidx.transition.CircularPropagation
import androidx.transition.TransitionInflater

import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Ingredient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {

    private lateinit var ingredientsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Determine how shared elements are handled
        sharedElementEnterTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.shared_element_transition)
        sharedElementReturnTransition =  ChangeBounds().apply {
            duration = 1500
            propagation = CircularPropagation()
            }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            //getting the passed in args
            val args =
                RecipeDetailFragmentArgs.fromBundle(
                    it
                )
            recipe_title.text = args.recipe.title
            Picasso.get().load(args.recipe.image).into(recipe_image)
            recipe_summary.text = args.recipe.summary
            recipe_duration.text = args.recipe.readyInMinutes.toString()

            val ingredients = StringBuilder()
            val ingredientList = args.recipe.extendedIngredients
            Log.d("IMAGE", "Image: ${ingredientList[1].image}")
            ingredientList.forEach { ingredient->
                ingredients.append('\u2666'.toString()).append("  ").append(ingredient.originalString).append("\n")
            }
            recipe_ingredients.text = ingredients

            //populating the ingredient recyclerView
            populateIngredientRecyclerView(view, ingredientList)

            val instructionsBuilder = StringBuilder()
            val recipeInstructions = args.recipe.instructions
            val instructions = recipeInstructions.split(".")
            val filteredInstructions = instructions.filterNot { it == "<p>"|| it == "</p"|| it == "</li>" || it == "<li>"|| it == "<ol>" }
            filteredInstructions.forEach { instruction->
                instructionsBuilder.append('\u2666'.toString()).append("  ").append(instruction).append("\n")
            }
            recipe_instructions.text = instructionsBuilder
        }
    }

    fun populateIngredientRecyclerView(view: View, ingredientList: List<Ingredient>){
        ingredientsRecyclerView = view.findViewById(R.id.recipeRecyclerView)
        ingredientsRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = IngredientsAdapter(ingredientList)
        ingredientsRecyclerView.adapter = adapter

        Log.d("IMAGE", "Image: ${ingredientList[1].image}")
    }

}
