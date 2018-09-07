package com.example.will_crawford.ms_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MovieHolder extends RecyclerView.ViewHolder {

    public TextView title, year, genre;

    MovieHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.title);
        genre = (TextView)itemView.findViewById(R.id.genre);
        year = (TextView)itemView.findViewById(R.id.year);
    }

    public void onClick(final Movie movie, final RecyclerTouchListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMovieClicked(movie);
            }
        });
    }
}
