package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterImpl implements Presenter {

    private List<Movie> movies;
    private MovieView movieView;

    PresenterImpl( MovieView movieView ){
        this.movieView = movieView;
    }

    @Override
    public void getMovie(String searchKey) {
        OMDbService omDbService = RetrofitClient.getInstance().getMovieService();
        Call<MovieResponse> call = omDbService.getMovie(searchKey);

        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                assert movieResponse != null;
                movies = movieResponse.getMovies();

                movieView.updateMovie(movies);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.d("Call Failed: ", t.getMessage());
            }
        });
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
