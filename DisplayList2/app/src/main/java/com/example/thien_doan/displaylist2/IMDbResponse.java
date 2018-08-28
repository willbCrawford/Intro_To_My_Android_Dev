package com.example.thien_doan.displaylist2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class IMDbResponse {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Release")
    private String releaseDate;

    @SerializedName("Ratings")
    private List<Review> reviews;

    @SerializedName("Plot")
    private String plot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Review> getReviews() {

        if (reviews != null)
            return reviews;

        else
            return new ArrayList<Review>();

    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
