package com.example.reservationreview.domain;

public class Review {
    private String review;
    private String stars;

    public Review(){

    }

    public Review(String review, String stars) {
        this.review = review;
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
