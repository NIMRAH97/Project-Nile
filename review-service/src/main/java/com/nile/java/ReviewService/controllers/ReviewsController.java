package com.nile.java.ReviewService.controllers;

import com.nile.java.ReviewService.models.Review;
import com.nile.java.ReviewService.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("/reviews")
    public void addReviews(@RequestHeader("Authorization") String token, @RequestBody Review review) {
        reviewsService.addReview(token, review);
    }

    @PutMapping("/reviews/{id}")
    public void updateReviews(@RequestHeader("Authorization") String token, @RequestBody Review review) {
        reviewsService.updateReview(token, review);
    }

    @DeleteMapping("/reviews/{id}")
    public void deleteReviews(@PathVariable("id") UUID id, @RequestBody Review review) {
        reviewsService.deleteReview(id);
    }

    @RequestMapping("/reviews/product/{id}")
    public Optional<Review> getReviewOfProduct(@PathVariable("id") UUID id) {
        return reviewsService.getReviewsForProduct(id);
    }

}