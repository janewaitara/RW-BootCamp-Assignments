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
            recipe_instructions.text = args.recipe.instructions
            val sb = StringBuilder()
            var count = 1
            args.recipe.extendedIngredients.forEach { ingredient->
                sb.append(count).append(". ").append(ingredient.originalString).append("\n")
                count++
            }
            recipe_ingredients.text = sb
        }
    }

}
