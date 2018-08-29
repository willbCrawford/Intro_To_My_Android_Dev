package com.example.will_crawford.moviesearchmvpimplementation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.List;

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieView, RecyclerTouchListener {

    TextInputEditText searchBar;
    private MoviesAdapter mAdapter;
    private List<Movie> movies;
    private SearchMoviePresenterImpl presenter;

    public static final String EXTRA_MESSAGE = "com.example.will_crawford.moviesearchmvpimplementation.MOVIE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(this);

        final Button searchButton = findViewById(R.id.search_button);
        presenter = new SearchMoviePresenterImpl(this);
        searchBar = findViewById(R.id.search_bar);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = searchBar.getText().toString();
                presenter.getMovie(message);
                hideKeyboard(v);
            }
        });

        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    searchButton.callOnClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void updateMovie(List<Movie> movies) {
        mAdapter.setMovies(movies);
        this.movies = movies;
    }

    @Override
    public void notValidSearch(String searchKey) {
        new AlertDialog.Builder(SearchMovieActivity.this)
                .setMessage("Your search is not valid!")
                .setPositiveButton("clear search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchBar.getText().clear();
                    }
                })
                .create().show();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(SearchMovieActivity.this, DisplayMovieActivity.class);
        intent.putExtra(EXTRA_MESSAGE, movie.getId());
        startActivity(intent);
    }
}
