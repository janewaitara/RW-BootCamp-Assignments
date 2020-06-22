package com.janewaitara.movieapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            //getting the passed in args
            val args = MovieDetailFragmentArgs.fromBundle(it)
            movie_title.text = args.movie.title
            movie_image.setImageResource(args.movie.image)
            movie_summary.text = args.movie.summary
            movie_release_date.text = args.movie.releaseDate
        }
    }

}
