package com.janewaitara.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() ,MovieListFragment.OnFragmentInteractionListener{

    companion object{
        const val INTENT_MOVIE_KEY = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showDetailsActivity(movie: Movie){
        val movieIntent = Intent(this,MovieDetail::class.java)
        movieIntent.putExtra(INTENT_MOVIE_KEY,movie)
        startActivity(movieIntent)
    }

    override fun onMovieListClicked(movie: Movie) {
        showDetailsActivity(movie)
    }
}
