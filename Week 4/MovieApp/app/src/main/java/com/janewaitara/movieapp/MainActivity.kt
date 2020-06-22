package com.janewaitara.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,MovieAdapter.MovieListClickListener{

    private lateinit var movieRecyclerView: RecyclerView

    companion object{
        const val INTENT_MOVIE_KEY = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        movieRecyclerView.adapter = MovieAdapter(this)

    }

    private fun showDetailsActivity(movie: Movie){
        val movieIntent = Intent(this,MovieDetail::class.java)
        movieIntent.putExtra(INTENT_MOVIE_KEY,movie)
        startActivity(movieIntent)

    }

    override fun movieItemClicked(movie: Movie) {
        showDetailsActivity(movie)
    }
}
