package com.example.will_crawford.moviesearchmvpimplementation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SearchMovieActivity extends AppCompatActivity implements MovieView {

    TextInputEditText searchBar;
    private MoviesAdapter mAdapter;
    private List<Movie> movies;
    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MoviesAdapter();

        final Button searchButton = findViewById(R.id.search_button);

        presenter = new PresenterImpl(this);

        searchBar = findViewById(R.id.search_bar);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = searchBar.getText().toString();

                if(!message.equals("")) {
                    presenter.getMovie(message);
                    movies = presenter.getMovies();
                }
                hideKeyboard(v);
            }
        });

        searchBar.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    searchButton.callOnClick();
                    return true;
                }
                return false;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movies.get(position);
                Toast.makeText(SearchMovieActivity.this, movie.getTitle() + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                new AlertDialog.Builder(SearchMovieActivity.this)
                        .setTitle(movies.get(position).getTitle())
                        .setMessage(movies.get(position).getYear())
                        .setPositiveButton("Dimiss", null)
                        .create()
                        .show();
            }
        }));
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    @Override
    public void updateMovie(List<Movie> movies) {
        mAdapter.setMovies(movies);
    }
}
