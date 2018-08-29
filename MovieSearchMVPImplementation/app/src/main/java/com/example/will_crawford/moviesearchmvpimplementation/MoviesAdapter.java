package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<Movie> movies;
    public RecyclerTouchListener recyclerTouchListener;

    public MoviesAdapter(RecyclerTouchListener recyclerTouchListener) {
        this.movies = new ArrayList<>();
        this.recyclerTouchListener = recyclerTouchListener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        final MovieHolder movieHolder = new MovieHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerTouchListener.onMovieClicked(movies.get(movieHolder.getPosition()));
            }
        });
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText((movie.getYear()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
