package com.project.springbootwebstore.service;

import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.entity.users.ReviewImagePath;
import com.project.springbootwebstore.repository.ReviewImagePathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewImagePathService {

    private final ReviewImagePathRepository imagePathRepository;

    public ReviewImagePath saveReviewImage(String path, Review review){
        ReviewImagePath reviewPath = new ReviewImagePath("reviews/" + path,review);
        imagePathRepository.save(reviewPath);
        return reviewPath;
    }

}
