package com.nile.java.ReviewService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;


@Document
public class Review {
    @Id
    private UUID reviewId;
    @Field
    private String buyerId;
    @Field
    private String orderId;
    @Field
    private int rating;
    @Field
    private String comment;
    @Field
    private String date;
    @Field
    private String sellerResponse;
    @Field
    private String sellerResponseDate;

    public Review() {
    }

    public Review(UUID reviewId, String buyerId, String orderId, int rating, String comment, String date, String sellerResponse, String sellerResponseDate) {
        this.reviewId = reviewId;
        this.buyerId = buyerId;
        this.orderId = orderId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.sellerResponse = sellerResponse;
        this.sellerResponseDate = sellerResponseDate;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSellerResponse() {
        return sellerResponse;
    }

    public void setSellerResponse(String sellerResponse) {
        this.sellerResponse = sellerResponse;
    }

    public String getSellerResponseDate() {
        return sellerResponseDate;
    }

    public void setSellerResponseDate(String sellerResponseDate) {
        this.sellerResponseDate = sellerResponseDate;
    }
}