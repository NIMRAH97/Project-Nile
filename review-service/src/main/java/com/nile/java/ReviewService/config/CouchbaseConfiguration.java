package com.nile.java.ReviewService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "california";
    }

    @Override
    public String getBucketName() {
        return "reviews";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}