package com.example.thien_doan.displaylist2;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MyReviewHolder extends RecyclerView.ViewHolder{

    public TextView reviewSource, reviewPercent;

    public MyReviewHolder(View itemView){

        super(itemView);

        reviewSource = itemView.findViewById(R.id.review_source);

        reviewPercent = itemView.findViewById(R.id.review_percent);

    }
}
