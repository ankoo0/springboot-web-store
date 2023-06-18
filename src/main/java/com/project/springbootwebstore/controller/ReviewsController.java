package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.ReviewDto;
import com.project.springbootwebstore.dto.ReviewRequestDto;
import com.project.springbootwebstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {



    private final ReviewService reviewService;

    @Autowired
    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{productId}")
    public @ResponseBody List<ReviewDto> getReviewsByProduct(@PathVariable("productId") Long productId, @RequestBody String page){
        return reviewService.getAllReviewsForProduct(productId,page).getContent();
    }


    @PostMapping(value = "/save", consumes ="multipart/form-data")
    public String saveReview(@AuthenticationPrincipal User user,
                             @ModelAttribute ReviewRequestDto reviewDto,
                             HttpServletRequest request){

//        @RequestParam("reviewText") String textArea,
//        @RequestParam("rating") String rating,
//        @RequestParam("reviewHead") String textInput,
//        @RequestParam(name = "reviewImages") List<MultipartFile> files,
//        @RequestHeader(required = false) String header){
//        System.out.println(textArea + " " + rating + " " + textInput + " ");
//        System.out.println(header);
//
        reviewService.saveReview(reviewDto,user);
//
        String referrer = request.getHeader("Referer");

        return "redirect:" + referrer;
    }
}
