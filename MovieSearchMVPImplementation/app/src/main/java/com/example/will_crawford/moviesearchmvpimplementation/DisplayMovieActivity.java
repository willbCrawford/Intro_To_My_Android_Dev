package com.example.will_crawford.moviesearchmvpimplementation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class DisplayMovieActivity extends AppCompatActivity implements DisplayMovieView {

    private RecyclerView recyclerView;
    private MovieInfoAdapter movieInfoAdapter;
    private DisplayMoviePresenterImpl presenter;
    TextView movieTitle;
    TextView movieYear;
    TextView movieReleaseDate;
    TextView moviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        presenter = new DisplayMoviePresenterImpl(this);

        recyclerView = findViewById(R.id.recycler_view_for_reviews);
        movieInfoAdapter = new MovieInfoAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(movieInfoAdapter);

        presenter.getIMDbInfo(getIntent().getStringExtra(SearchMovieActivity.EXTRA_MESSAGE));

        movieTitle = findViewById(R.id.display_movie_title);
        movieYear = findViewById(R.id.display_movie_year);
        movieReleaseDate = findViewById(R.id.display_movie_release_date);
        moviePlot = findViewById(R.id.plot);
    }

    @Override
    public void updateMovieInfo(MovieInfoResponse movieInfoResponse, List<Review> reviews) {
        movieTitle.setText(movieInfoResponse.getTitle());
        movieYear.setText(movieInfoResponse.getYear());
        movieReleaseDate.setText(movieInfoResponse.getReleaseDate());
        moviePlot.setText(movieInfoResponse.getPlot());

        movieInfoAdapter.setReviews(reviews);
    }
}
