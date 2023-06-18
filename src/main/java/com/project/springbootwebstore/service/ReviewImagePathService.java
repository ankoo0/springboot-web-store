package com.project.springbootwebstore.service;

import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.entity.users.ReviewImagePath;
import com.project.springbootwebstore.repository.ReviewImagePathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewImagePathService {

    private final ReviewImagePathRepository imagePathRepository;


    @Autowired
    public ReviewImagePathService(ReviewImagePathRepository imagePathRepository) {
        this.imagePathRepository = imagePathRepository;
    }

    public ReviewImagePath saveReviewImage(String path, Review review){
        ReviewImagePath reviewPath = new ReviewImagePath("reviews/" + path,review);
        imagePathRepository.save(reviewPath);
        return reviewPath;
    }

}
