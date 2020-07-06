package com.janewaitara.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.launch

class MovieListFragment : Fragment(), MovieAdapter.MovieListClickListener {

    private lateinit var movieRecyclerView: RecyclerView

    private lateinit var movieViewModel: MovieViewModel

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
        val adapter = MovieAdapter(this)
        movieRecyclerView.adapter = adapter

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        lifecycleScope.launch{
            movieViewModel.allMovies.observe(viewLifecycleOwner, Observer{ movies ->
                movies?.let { adapter.setMovies(it) }
            })
        }
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
