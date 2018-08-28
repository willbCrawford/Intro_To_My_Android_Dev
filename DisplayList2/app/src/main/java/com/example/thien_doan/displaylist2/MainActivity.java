package com.example.thien_doan.displaylist2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private  MoviesAdapter mAdapter;
    private Retrofit retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MoviesAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movies.get(position);
                Toast.makeText(MainActivity.this, movie.getTitle() + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(movies.get(position).getTitle())
                        .setMessage(movies.get(position).getYear())
                        .setPositiveButton("Dimiss", null)
                        .create()
                        .show();
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
