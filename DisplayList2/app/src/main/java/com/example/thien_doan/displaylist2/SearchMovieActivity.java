package com.example.thien_doan.displaylist2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchMovieActivity extends AppCompatActivity {

    TextInputEditText searchBar;
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private  MoviesAdapter mAdapter;
    private Retrofit retrofitClient;

    public static final String EXTRA_MESSAGE = "com.example.thien_doan.displaylist2.MOVIE_TO_SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search_movie);

         searchBar = findViewById(R.id.search_bar);

        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = searchBar.getText().toString();

                getMovie(message);

            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MoviesAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Movie movie = movies.get(position);
//                Toast.makeText(SearchMovieActivity.this, movie.getTitle() + " is selected", Toast.LENGTH_LONG).show();

                return;

            }

            @Override
            public void onLongClick(View view, int position) {
//                new AlertDialog.Builder(SearchMovieActivity.this)
//                        .setTitle(movies.get(position).getTitle())
//                        .setMessage(movies.get(position).getYear())
//                        .setPositiveButton("Dimiss", null)
//                        .create()
//                        .show();

                Intent intent = new Intent(SearchMovieActivity.this, Display_MovieActivity.class);

                intent.putExtra(EXTRA_MESSAGE, movies.get(position).getTitle());

                startActivity(intent);

            }
        }));

        retrofitClient = RetrofitSingleton.getRetrofit();


    }

    private void getMovie(String searchKey) {

        OMDbService Api = retrofitClient.create(OMDbService.class);

        Call<MovieResponse> call = Api.getMovie(searchKey);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                movies = movieResponse.getMovies();

                mAdapter.setMovies(movieResponse.getMovies());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Call Failed: ", t.getMessage());
            }
        });
    }

}
