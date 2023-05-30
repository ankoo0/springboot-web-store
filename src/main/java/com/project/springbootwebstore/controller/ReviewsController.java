package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.model.dto.ReviewDto;
import com.project.springbootwebstore.model.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {



    private final ReviewService reviewService;

    @Autowired
    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{productId}")
    public List<ReviewDto> getReviewsByProduct(@PathVariable("productId") Long productId, @RequestBody String page){
        System.out.println(reviewService.getAllReviewsForProduct(productId,page).getContent());
        System.out.println(productId + " ccccccccccc " + page);
        return reviewService.getAllReviewsForProduct(productId,page).getContent();
    }
}
