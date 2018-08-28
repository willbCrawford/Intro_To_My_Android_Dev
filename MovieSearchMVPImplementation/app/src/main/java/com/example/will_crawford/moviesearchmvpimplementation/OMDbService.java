package com.example.will_crawford.moviesearchmvpimplementation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDbService {

    String BASE_URL = "http://www.omdbapi.com";

    @GET("/?apiKey=bbbd7c62")
    Call<MovieResponse> getMovie(
            @Query("s") String movieTitle
    );

    @GET("/?apiKey=bbbd7c62")
    Call<MovieResponse> getIMDbInfo(
            @Query("t") String imdbInfo
    );

}
