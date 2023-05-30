package com.project.springbootwebstore.model.dto.mappers;

import com.project.springbootwebstore.model.dto.ReviewDto;
import com.project.springbootwebstore.model.entity.users.Review;
import com.project.springbootwebstore.model.entity.users.ReviewImagePath;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReviewDtoMapper implements Function<Review, ReviewDto> {


    @Override
    public ReviewDto apply(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getUser().getUsername(),
                review.getUser().getAvatarPath(),
                review.getLikedUsers().stream().count(),
                review.getDislikedUsers().stream().count(),
                review.getRating(),
                review.getReview(),
                review.getDateTime(),
                review.getReviewImagesPaths().stream().map(ReviewImagePath::getImagePath).toList()
        );
    }
}
