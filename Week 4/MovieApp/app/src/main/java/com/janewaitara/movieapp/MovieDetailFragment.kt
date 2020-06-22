package com.janewaitara.movieapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    lateinit var movie: Movie


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
            movie_title.text = args.movieTitle
            movie_image.setImageResource(args.movieImage)
            movie_summary.text = args.movieSummary
            movie_release_date.text = args.movieReleaseDate
        }
    }


    companion object {

        private val ARG_LIST = "list" //help to access the movie within the bundle

        fun newInstance(movie: Movie):MovieDetailFragment {

            val bundle = Bundle() //object with key,value pairs
            bundle.putParcelable(ARG_LIST,movie) //put the list into the Bundle
            val fragment = MovieDetailFragment ()
            fragment.arguments = bundle //putting the bundle inside the fragment
            return  fragment
        }

    }
}
