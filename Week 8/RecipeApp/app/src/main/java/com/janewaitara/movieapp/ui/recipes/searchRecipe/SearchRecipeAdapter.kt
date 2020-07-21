package com.janewaitara.movieapp.ui.recipes.searchRecipe


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

class SearchRecipeAdapter(val clickListener: SearchRecipeListClickListener) : RecyclerView.Adapter<SearchRecipeAdapter.SearchRecipeViewHolder>() {

    private var recipeList = emptyList<SearchRecipe>() //Cached copy of recipes

    //notifies all other objects whenever a View is clicked
    interface SearchRecipeListClickListener{
        fun searchRecipeItemClicked(recipe: SearchRecipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list,parent,false)
        return SearchRecipeViewHolder(
            view
        )
    }

    override fun getItemCount() = recipeList.size

    internal fun setSearchRecipes(recipes: List<SearchRecipe>)  {
        this.recipeList = recipes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchRecipeViewHolder, position: Int) {

        Picasso.get()
            .load(recipeList[position].image)
            .fit()

            .centerCrop()
            .into(holder.recipeImage)

        holder.recipeName.text = recipeList[position].title

        holder.itemView.setOnClickListener {
            clickListener.searchRecipeItemClicked(recipeList[position])
        }
    }
    class SearchRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var recipeName = itemView.findViewById (R.id.recipe_name) as TextView
        var recipeImage = itemView.findViewById (R.id.recipe_image_card) as ImageView

    }
}