package com.myinnovation.customer.Models;

public class Reviews {
    String reviewTitle;
    String reviewBody;

    public Reviews(){}

    public Reviews(String reviewTitle, String reviewBody) {
        this.reviewTitle = reviewTitle;
        this.reviewBody = reviewBody;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }
}
