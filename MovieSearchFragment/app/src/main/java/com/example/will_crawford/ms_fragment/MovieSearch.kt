package com.example.will_crawford.ms_fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import java.lang.RuntimeException

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MovieSearch.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MovieSearch.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MovieSearch : Fragment(), View.OnClickListener, SearchMovieView, RecyclerTouchListener {
    private val presenterImpl = SearchMoviePresenterImpl(this)
    private val movieAdapter: MoviesAdapter = MoviesAdapter(this)
    private var onMovieClicked: OnMovieClicked? = null
    lateinit var searchBar: EditText
    lateinit var searchButton: Button
    var movies: MutableList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is OnMovieClicked){
            onMovieClicked = context
        }else{
            throw RuntimeException(context.toString() + " must implement onMovieClicked")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener(this)

        searchBar = view.findViewById(R.id.search_bar)

        searchBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchButton.callOnClick()
                return@OnKeyListener true
            }
            false
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = movieAdapter
    }

    override fun onDetach() {
        super.onDetach()
        onMovieClicked = null
    }

    override fun onClick(v: View) {
        if(!searchBar.text.toString().equals("")){
            presenterImpl.getMovie(searchBar.text.toString())
        }
        hideKeyboard(v)
    }

    private fun hideKeyboard(v: View) {
        val imm = (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)!!
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun updateMovie(movies: MutableList<Movie>) {
        movieAdapter.setMovies(movies)
        this.movies = movies
    }

    override fun notValidSearch() {
        AlertDialog.Builder(activity!!)
                .setTitle("Your search is not valid!")
                .setMessage("Please only use alphabetic characters!")
                .setNegativeButton("clear search") { _, _ -> searchBar.text.clear() }
                .create().show()
    }

    override fun onMovieClicked(movie: Movie?) {
        onMovieClicked?.onClicked(movie?.id.toString())
    }

    interface OnMovieClicked{
        fun onClicked(message: String)
    }
}
