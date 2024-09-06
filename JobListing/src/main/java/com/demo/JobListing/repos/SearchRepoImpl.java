package com.demo.JobListing.repos;

import java.util.*;

import com.demo.JobListing.models.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class SearchRepoImpl implements  SearchRepo {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    public List<Post> findByText(String text){
        final List<Post> jobs = new ArrayList<>();

        MongoDatabase database = client.getDatabase("demo");
        MongoCollection<Document> collection = database.getCollection("JobSearchDB");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                        new Document("$search",
                        new Document("index", "default").append("text", new Document("query", text).append("path", Arrays.asList("techs", "desc")))),
                        new Document("$sort", new Document("exp", 1L)),
                        new Document("$limit", 3L)));

        result.forEach((doc) -> jobs.add(converter.read(Post.class, doc)));

        return  jobs;
    }

}
