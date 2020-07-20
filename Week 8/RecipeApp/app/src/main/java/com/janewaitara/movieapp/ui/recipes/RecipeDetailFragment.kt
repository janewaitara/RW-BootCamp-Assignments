package com.janewaitara.movieapp.ui.recipes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.janewaitara.movieapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            args.recipe.extendedIngredients.forEach { ingredient->
                ingredients.append('\u2666'.toString()).append("  ").append(ingredient.originalString).append("\n")
            }
            recipe_ingredients.text = ingredients

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

}
