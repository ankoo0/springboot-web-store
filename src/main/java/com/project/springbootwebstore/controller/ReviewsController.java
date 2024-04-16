package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.review.ReviewResponse;
import com.project.springbootwebstore.dto.ReviewRequestDto;
import com.project.springbootwebstore.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewService reviewService;

    @PostMapping("/{productId}")
    public @ResponseBody List<ReviewResponse> getReviewsByProduct(@PathVariable("productId") Long productId, @RequestBody String page){
        return reviewService.getAllReviewsForProduct(productId,page).getContent();
    }

    @PostMapping(value = "/save", consumes ="multipart/form-data")
    public String saveReview(@AuthenticationPrincipal User user,
                             @ModelAttribute ReviewRequestDto reviewDto,
                             HttpServletRequest request){

        reviewService.saveReview(reviewDto,user);

        String referrer = request.getHeader("Referer");

        return "redirect:" + referrer;
    }
}
