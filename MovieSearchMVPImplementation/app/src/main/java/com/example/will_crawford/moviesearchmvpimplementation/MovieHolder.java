package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MovieHolder extends RecyclerView.ViewHolder {

    public TextView title, year, genre;

    public MovieHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.title);
        genre = (TextView)itemView.findViewById(R.id.genre);
        year = (TextView)itemView.findViewById(R.id.year);
    }
}
