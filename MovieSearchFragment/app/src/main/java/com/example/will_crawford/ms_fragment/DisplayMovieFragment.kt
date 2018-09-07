package com.example.will_crawford.ms_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val MOVIE_TO_SEARCH = "movieToSearch"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DisplayMovieFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DisplayMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DisplayMovieFragment : Fragment(), DisplayMovieView {
    // TODO: Rename and change types of parameters
    private var movieToSearch: String? = null
    private var param2: String? = null

    lateinit var recyclerView: RecyclerView
    private var movieInfoAdapter: MovieInfoAdapter = MovieInfoAdapter()
    private var presenter: DisplayMoviePresenterImpl = DisplayMoviePresenterImpl(this)
    lateinit var movieTitle: TextView
    lateinit var movieYear: TextView
    lateinit var movieReleaseDate: TextView
    lateinit var moviePlot: TextView

    override fun onStart() {
        super.onStart()
        val args = arguments
        movieToSearch = args?.getString(MOVIE_TO_SEARCH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieToSearch = it.getString(MOVIE_TO_SEARCH)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_for_reviews)
        movieInfoAdapter = MovieInfoAdapter()

        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = movieInfoAdapter

        presenter.getIMDbInfo(movieToSearch)

        movieTitle = view.findViewById(R.id.display_movie_title)
        movieYear = view.findViewById(R.id.display_movie_year)
        movieReleaseDate = view.findViewById(R.id.display_movie_release_date)
        moviePlot = view.findViewById(R.id.plot)

        presenter.getIMDbInfo(movieToSearch)
        Log.d("Params I got", movieToSearch)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param movieToSearch Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShowMovieList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(movieToSearch: String, param2: String) =
                DisplayMovieFragment().apply {
                    arguments = Bundle().apply {
                        putString(MOVIE_TO_SEARCH, movieToSearch)
                    }
                }
    }

    override fun updateMovieInfo(movieInfoResponse: MovieInfoResponse, reviews: MutableList<Review>?) {
        movieTitle.text = movieInfoResponse.title
        movieYear.text = movieInfoResponse.year
        movieReleaseDate.text = movieInfoResponse.releaseDate
        moviePlot.text = movieInfoResponse.plot

        movieInfoAdapter.setReviews(reviews)
    }
}
