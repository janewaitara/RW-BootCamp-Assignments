package com.janewaitara.movieapp.ui.movies

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.storage.MovieSharedPrefs
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Movie
import kotlinx.coroutines.launch

class MovieListFragment : Fragment(), MovieAdapter.MovieListClickListener {

    private lateinit var movieRecyclerView: RecyclerView

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var loginPrefs: MovieSharedPrefs



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
        loginPrefs = MovieSharedPrefs()
    }

    override fun movieItemClicked(movie: Movie) {
      showDetailsActivity(movie)
    }

    private fun showDetailsActivity(movie: Movie){
        view?.let {
            val action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                    movie
                ) //trigger the navigation and passing data
            it.findNavController().navigate(action)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.log_out -> {
                loginPrefs.setLoginStatus(false)
                view?.let {
                    it.findNavController().navigate(R.id.action_movieListFragment_to_loginFragment)
                }
            }
        }
        return true
    }
}
