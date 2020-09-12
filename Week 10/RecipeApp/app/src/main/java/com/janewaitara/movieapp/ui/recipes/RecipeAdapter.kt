package com.janewaitara.movieapp.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.squareup.picasso.Picasso

class RecipeAdapter(val clickListener: RecipeListClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipeList = emptyList<Recipe>() //Cached copy of recipes

    //notifies all other objects whenever a View is clicked
    interface RecipeListClickListener{
        fun recipeItemClicked(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list,parent,false)
        return RecipeViewHolder(
            view
        )
    }

    override fun getItemCount() = recipeList.size

    internal fun setRecipes(recipes: List<Recipe>)  {
        this.recipeList = recipes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        Picasso.get()
            .load(recipeList[position].image)
            .fit()

            .centerCrop()
            .into(holder.recipeImage)
        holder.recipeName.text = recipeList[position].title
        holder.itemView.setOnClickListener {
            clickListener.recipeItemClicked(recipeList[position])
        }
    }
    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var recipeName = itemView.findViewById (R.id.recipe_name) as TextView
        var recipeImage = itemView.findViewById (R.id.recipe_image_card) as ImageView

    }
}