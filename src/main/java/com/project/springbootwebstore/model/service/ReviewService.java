package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.dto.ReviewDto;
import com.project.springbootwebstore.model.dto.mappers.ReviewDtoMapper;
import com.project.springbootwebstore.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewDtoMapper reviewDtoMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
    }

    public Page<ReviewDto> getAllReviewsForProduct(Long productId,String page){
        final int REVIEW_PAGE_SIZE = 2;
        return reviewRepository.
                findAllByProductId(PageRequest.of(Integer.parseInt(page),REVIEW_PAGE_SIZE),productId).
                map(reviewDtoMapper);
    }

    public Long getProductReviewsCount(Long productId){
        return reviewRepository.countReviewsByProductId(productId);
    }

}
