package com.project.springbootwebstore.model.dto;

import com.project.springbootwebstore.model.entity.users.User;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewDto {

    private final Long id;
    private final String username;
    private final String userAvatar;
    private final Long likes;
    private final Long dislikes;
    private final Double rating;
    private final String review;
    private final String dateTime;
    private final List<String> reviewImagePaths;


    public ReviewDto(Long id,
                     String username,
                     String userAvatar,
                     Long likes,
                     Long dislikes,
                     Double rating,
                     String review,
                     LocalDateTime dateTime,
                     List<String> reviewImagePaths) {
        this.id = id;
        this.username = username;
        this.userAvatar=userAvatar;
        this.likes = likes;
        this.dislikes = dislikes;
        this.rating = rating;
        this.review = review;
        this.dateTime=dateTime.toString();
        this.reviewImagePaths = reviewImagePaths;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getLikes() {
        return likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public Double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public List<String> getReviewImagePaths() {
        return reviewImagePaths;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUserAvatar() {
        return userAvatar;
    }
}
