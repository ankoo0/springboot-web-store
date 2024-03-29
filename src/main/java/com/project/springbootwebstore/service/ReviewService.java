package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.ReviewDto;
import com.project.springbootwebstore.dto.ReviewRequestDto;
import com.project.springbootwebstore.dto.mappers.ReviewDtoMapper;
import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.repository.ProductRepository;
import com.project.springbootwebstore.repository.ReviewRepository;
import com.project.springbootwebstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReviewService {

    @Value("${review.image.directory}")
    private String imageDirectory;
    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final UserRepository userRepository;
    private final ReviewImagePathService imagePathService;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewDtoMapper reviewDtoMapper, UserRepository userRepository, ReviewImagePathService imagePathService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
        this.userRepository = userRepository;
        this.imagePathService = imagePathService;
        this.productRepository = productRepository;
    }





    public Page<ReviewDto> getAllReviewsForProduct(Long productId,String page){
        final int REVIEW_PAGE_SIZE = 2;
        return reviewRepository.
                findAllByProductId(PageRequest.of(Integer.parseInt(page),REVIEW_PAGE_SIZE),productId).
                map(reviewDtoMapper);
    }


    public Review saveReview(ReviewRequestDto reviewDto, User user){
        Long productId = reviewDto.getProductId();
        String productReviewDir = imageDirectory + productId +"\\";

        Review review = new Review(
                reviewDto.getTitle(),
                new com.project.springbootwebstore.entity.users.User(userRepository.findByUsername(user.getUsername()).get().getId()),
                reviewDto.getRating(),
                reviewDto.getReview(),
                LocalDateTime.now(),
                productRepository.getReferenceById(productId)
                );

        reviewRepository.save(review);


        AtomicInteger counter = new AtomicInteger(1);
        reviewDto.getReviewImages().forEach(img-> {
            try {
                String imgCount = counter.get() >10? String.valueOf(counter.get()) : "0"+counter;
                String filename = user.getUsername() + "_" + imgCount +  "." + Objects.requireNonNull(img.getOriginalFilename()).substring(img.getOriginalFilename().lastIndexOf(".") + 1);
                Files.createDirectories(Paths.get(imageDirectory + productId));
                File imageFile = new File(imageDirectory + productId, filename);
                img.transferTo(imageFile);
                counter.getAndIncrement();
                imagePathService.saveReviewImage( productId + "/" +filename, review);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return review;
    }

    public Long getProductReviewsCount(Long productId){
        return reviewRepository.countReviewsByProductId(productId);
    }

}
