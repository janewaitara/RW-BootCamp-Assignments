package com.janewaitara.movieapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_LIST)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
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
