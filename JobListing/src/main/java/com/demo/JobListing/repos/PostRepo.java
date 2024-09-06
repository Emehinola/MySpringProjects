package com.demo.JobListing.repos;

import com.demo.JobListing.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo  extends MongoRepository<Post, Integer> {

}
