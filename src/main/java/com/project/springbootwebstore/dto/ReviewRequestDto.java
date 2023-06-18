package com.project.springbootwebstore.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public final class ReviewRequestDto {

    private final String title;
    private final String review;
    private final Double rating;
    private final Long productId;
    private final List<MultipartFile>  reviewImages;

    public ReviewRequestDto(String title,
                            String review,
                            Double rating,
                            Long productId,
                            List<MultipartFile>  reviewImages) {
        this.title = title;
        this.review = review;
        this.rating = rating;
        this.productId = productId;
        this.reviewImages=reviewImages;
    }

    public List<MultipartFile> getReviewImages() {
        return reviewImages;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public Double getRating() {
        return rating;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ReviewRequestDto{" +
                "title='" + title + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", productId=" + productId +
                ", reviewImages=" + reviewImages +
                '}';
    }
}
