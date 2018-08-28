package com.example.thien_doan.displaylist2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView title, year, genre;

    public MyViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.title);
        genre = (TextView)itemView.findViewById(R.id.genre);
        year = (TextView)itemView.findViewById(R.id.year);
    }
}
