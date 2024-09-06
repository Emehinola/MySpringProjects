package com.demo.JobListing.controllers;

import com.demo.JobListing.models.Post;
import com.demo.JobListing.repos.PostRepo;
import com.demo.JobListing.repos.SearchRepo;
import com.demo.JobListing.repos.SearchRepoImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class PostController {

    @Autowired
    PostRepo repo;

    @Autowired
    SearchRepo searchRepo;

    @GetMapping("/")
    public  void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return repo.findAll();
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return repo.save(post);
    }

    @GetMapping("/posts/{searchParam}")
    public  List<Post> search(@PathVariable("searchParam") String searchParam){
        return searchRepo.findByText(searchParam);
    }

}
