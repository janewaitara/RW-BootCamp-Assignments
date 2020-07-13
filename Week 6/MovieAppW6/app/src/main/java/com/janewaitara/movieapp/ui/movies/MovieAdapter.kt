package com.janewaitara.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Movie

class MovieAdapter(val clickListener: MovieListClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = emptyList<Movie>() //Cached copy of movies

    //notifies all other objects whenever a View is clicked
    interface MovieListClickListener{
        fun movieItemClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list,parent,false)
        return MovieViewHolder(
            view
        )
    }

    override fun getItemCount() = movieList.size

    internal fun setMovies(movies: List<Movie>)  {
        this.movieList = movies
        notifyDataSetChanged()
    }

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