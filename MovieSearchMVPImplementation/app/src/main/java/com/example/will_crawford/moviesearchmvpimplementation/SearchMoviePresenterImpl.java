package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviePresenterImpl implements SearchMoviePresenter {

    private List<Movie> movies;
    private SearchMovieView searchMovieView;

    SearchMoviePresenterImpl(SearchMovieView searchMovieView){
        this.searchMovieView = searchMovieView;
    }

    @Override
    public void getMovie(String searchKey) {
        OMDbService omDbService = RetrofitClient.getInstance().getMovieService();
        Call<MoviesResponse> call = omDbService.getMovie(searchKey);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                if (response.isSuccessful() && moviesResponse != null) {
                    movies = moviesResponse.getMovies();
                    searchMovieView.updateMovie(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.d("Call Failed: ", t.getMessage());
            }
        });
    }
}
