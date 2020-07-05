package com.janewaitara.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val clickListener: MovieListClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = mutableListOf(
        Movie(
            "94",
            "The Grudge",
            "A house is cursed by a vengeful ghost that dooms those who enter it with a violent death.",
            "January 3rd 2020",
            R.drawable.thegrudge
        ),
        Movie("100",
            "The Call of the Wild ",
            "A sled dog struggles for survival in the wilds of the Yukon.",
             "February 21st 2020",
            R.drawable.thecallofwind

        ), Movie("97",
            "A Quiet Place Part II ",
            "Following the events at home, the Abbott family now face the terrors of the outside world. " +
                    "Forced to venture into the unknown, they realize the creatures that hunt by sound are not the only threats lurking beyond the sand path.",
             "March 20th 2020",
            R.drawable.quiet_place
        ),
        Movie(
            "95",
            "Underwater",
            "A crew of oceanic researchers working for a deep sea drilling company" +
                    " try to get to safety after a mysterious earthquake devastates their deepwater research " +
                    "and drilling facility located at the bottom of the Mariana Trench.",
            "January 10th 2020",
            R.drawable.under_water
        ),
        Movie(
            "83",
            "Like a Boss",
            "Two friends with very different ideals start a beauty company together. O" +
                    "ne is more practical while the other wants to earn her fortune and live a lavish lifestyle.",
            "January 10th 2020",
            R.drawable.like_boss
        ),
        Movie(
            "124",
            "Bad Boys for life",
            "Miami detectives Mike Lowrey and Marcus Burnett must face off against a " +
                    "mother-and-son pair of drug lords who wreak vengeful havoc on their city.",
            "January 17th 2020",
            R.drawable.bad_boys_for_life
        ),
        Movie(
            "101",
            "Dolitle",
            "A physician who can talk to animals embarks on an adventure to find a " +
                    "legendary island with a young apprentice and a crew of strange pets.",
            "January 17th 2020",
            R.drawable.dolitte
        ),
        Movie(
            "113",
            "The Gentlemen",
            "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.",
            "January 24th 2020",
            R.drawable.gentle_men
        ),
        Movie(
            "94",
            "The turning",
            "A young governess is hired by a man who has become responsible for his young nephew and niece after their parents' deaths. A modern take on Henry James' novella \"The Turn of the Screw.\"",
            "January 24th 2020",
            R.drawable.turning
        ),
        Movie(
            "109",
            "Rhythm Section",
            "A woman seeks revenge against those who orchestrated a plane crash that killed her family.",
            "January 31st 2020",
            R.drawable.rhythm
        ),
        Movie(
            "109",
            "Birds of Prey",
            "After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
            "February 7th 2020",
            R.drawable.birds_of_prey
        ),
        Movie(
            "99",
            "Sonic the Hedgehog",
            "After discovering a small, blue, fast hedgehog, a small-town police officer must help him defeat an evil genius who wants to do experiments on him.",
            "February 14th 2020",
            R.drawable.hedgehog
        ),
        Movie(
            "109",
            "Fantasy island",
            "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
            "February 14th 2020",
            R.drawable.fantasy_island
        )
    )

    //notifies all other objects whenever a View is clicked
    interface MovieListClickListener{
        fun movieItemClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.movieImage.setImageResource(movieList[position].image)
        holder.movieName.text = movieList[position].title
        holder.itemView.setOnClickListener {
            clickListener.movieItemClicked(movieList[position])
        }
    }
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var movieName = itemView.findViewById (R.id.movie_name) as TextView
        var movieImage = itemView.findViewById (R.id.movie_image_card) as ImageView

    }
}