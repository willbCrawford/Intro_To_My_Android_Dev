package com.example.thien_doan.displaylist2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDbService {

    String BASE_URL = "http://www.omdbapi.com";

    @GET("/?apiKey=bbbd7c62")
    Call<MovieResponse> getMovie(
            @Query("s") String movieTitle

//            @Query("apiKey") String apiKey
//            @Query("r") String response
    );

    @GET("/?apiKey=bbbd7c62")
    Call<IMDbResponse> getIMDbInfo(
            @Query("t") String title
    );

}
