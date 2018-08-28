package com.example.thien_doan.displaylist2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Display_MovieActivity extends AppCompatActivity {

    private List<Review> reviews;
    private RecyclerView recyclerView;
    private  IMDbAdapter rAdapter;
    private Retrofit retrofitClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display__movie);

        recyclerView = findViewById(R.id.recycler_view_for_reviews);
        rAdapter = new IMDbAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);

        retrofitClient = RetrofitSingleton.getRetrofit();

        getMovie(getIntent().getStringExtra(SearchMovieActivity.EXTRA_MESSAGE));

    }

    public void getMovie(String searchKey){

        OMDbService Api = retrofitClient.create(OMDbService.class);

        Call<IMDbResponse> call = Api.getIMDbInfo(searchKey);

        call.enqueue(new Callback<IMDbResponse>() {
            @Override
            public void onResponse(Call<IMDbResponse> call, Response<IMDbResponse> response) {
                IMDbResponse imDbResponse = response.body();

                reviews = imDbResponse.getReviews();

                rAdapter.setReviews(reviews);
            }

            @Override
            public void onFailure(Call<IMDbResponse> call, Throwable t) {

                Log.d("Call Failed", t.getMessage());

            }
        });

    }

}
