package com.example.will_crawford.ms_fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MovieSearch.OnMovieClicked{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieSearch = MovieSearch()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.movie_frame, movieSearch)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClicked(message: String) {
        val displayMovieFragment = DisplayMovieFragment()
        val args = Bundle()
        args.putString(MOVIE_TO_SEARCH, message)
        args.putString(BLANK_PARAM, "")
        displayMovieFragment.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.movie_frame, displayMovieFragment)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }
}
