package com.nile.java.ReviewService.models;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewsRepository extends CouchbaseRepository<Review, UUID> {
}