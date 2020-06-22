package com.janewaitara.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MovieListFragment : Fragment(), MovieAdapter.MovieListClickListener {

    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieRecyclerView = view.findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        movieRecyclerView.adapter = MovieAdapter(this)

    }

    override fun movieItemClicked(movie: Movie) {
      showDetailsActivity(movie)
    }

    private fun showDetailsActivity(movie: Movie){
        view?.let {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie) //trigger the navigation and passing data
            it.findNavController().navigate(action)
        }

    }
}
