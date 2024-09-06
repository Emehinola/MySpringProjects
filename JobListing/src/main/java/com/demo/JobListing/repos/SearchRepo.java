package com.demo.JobListing.repos;
import com.demo.JobListing.models.Post;
import java.util.*;


public interface SearchRepo {

    List<Post> findByText(String text);
}
