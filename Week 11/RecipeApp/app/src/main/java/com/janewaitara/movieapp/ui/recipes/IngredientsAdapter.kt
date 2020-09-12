package com.janewaitara.movieapp.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Ingredient
import com.janewaitara.movieapp.model.Recipe
import com.squareup.picasso.Picasso

class IngredientsAdapter(var ingredientsList: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_list,parent,false)
        return IngredientsViewHolder(
            view
        )
    }

    override fun getItemCount(): Int  = ingredientsList.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        Picasso.get()
            .load(ingredientsList[position].image)
            .fit()
            .centerCrop()
            .into(holder.ingredientImage)

        holder.ingredientName.text = ingredientsList[position].originalString
    }

    class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ingredientName = itemView.findViewById (R.id.ingredient_name) as TextView
        var ingredientImage = itemView.findViewById (R.id.ingredient_image) as ImageView
    }
}