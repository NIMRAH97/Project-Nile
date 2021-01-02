package com.nile.java.ReviewService.services;

import com.nile.java.ReviewService.models.Review;
import com.nile.java.ReviewService.models.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReviewsRepository reviewsRepository;

    public void addReview(String token, Review review) {

        // To Add review OrderId is needed.
        // OrderId along with token is passed to OrderService for validation.

        // Validate Jwt
        if (authenticateBuyer(token).matches("OK") && matchBuyerId(token, review.getOrderId())) {
            reviewsRepository.save(review);
        }
    }

    public Optional<Review> getReviewsForProduct(UUID id) {
        return reviewsRepository.findById(id);
    }

    public void updateReview(String token, Review review) {
        if (authenticateBuyer(token).matches("OK")) {
            reviewsRepository.save(review);
        }
    }

    public void deleteReview(UUID id) {
        reviewsRepository.deleteById(id);
    }

    public String authenticateBuyer(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://buyer-authentication-service/buyer/authenticate", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    public Boolean matchBuyerId(String token, String orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://buyer-authentication-service/buyer/userName", HttpMethod.GET, entity, String.class);
        String userName = responseEntity.getBody();
        if (userName != null) {
            ResponseEntity<Object[]> responseEntityBuyerObject = restTemplate.exchange("http://buyer-service/buyers/search/" + userName, HttpMethod.GET, entity, Object[].class);
            Object[] buyerObject = responseEntityBuyerObject.getBody();

            ResponseEntity<Object[]> responseEntityOrderObject = restTemplate.exchange("http://order-service/orders/" + orderId, HttpMethod.GET, entity, Object[].class);
            Object[] orderObject = responseEntityOrderObject.getBody();

            try {
                if (buyerObject[0].toString().matches(orderObject[1].toString())) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
