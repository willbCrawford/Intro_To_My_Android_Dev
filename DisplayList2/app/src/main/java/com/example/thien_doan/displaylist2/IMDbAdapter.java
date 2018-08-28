package com.example.thien_doan.displaylist2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class IMDbAdapter extends RecyclerView.Adapter<MyReviewHolder> {

    private List<Review> reviews;

    public IMDbAdapter() {
        reviews = new ArrayList<Review>();
    }

    @NonNull
    @Override
    public MyReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_row, parent, false);

        return new MyReviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReviewHolder holder, int position) {

        Review review = reviews.get(position);

        holder.reviewSource.setText(review.source);

        holder.reviewPercent.setText(review.rating);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }
}
