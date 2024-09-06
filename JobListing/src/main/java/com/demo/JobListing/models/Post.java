package com.demo.JobListing.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobSearchDB")
public class Post {
    private String desc;
    private int exp;
    private String profile;
    private String[] techs;

    public Post(){}

    public Post(String desc, int exp, String profile, String[] techs){
        this.desc = desc;
        this.profile = profile;
        this.exp = exp;
        this.techs = techs;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String[] getTechs() {
        return techs;
    }

    public void setTechs(String[] techs) {
        this.techs = techs;
    }
}
