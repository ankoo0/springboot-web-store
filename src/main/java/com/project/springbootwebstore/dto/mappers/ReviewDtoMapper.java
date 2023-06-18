package com.project.springbootwebstore.dto.mappers;

import com.project.springbootwebstore.dto.ReviewDto;
import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.entity.users.ReviewImagePath;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
 public final class ReviewDtoMapper implements Function<Review, ReviewDto> {


    @Override
    public ReviewDto apply(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getUser().getUsername(),
                review.getUser().getAvatarPath(),
                (long) review.getLikedUsers().size(),
                (long) review.getDislikedUsers().size(),
                review.getRating(),
                review.getTitle(),
                review.getReview(),
                review.getDateTime(),
                review.getReviewImagesPaths().stream().map(ReviewImagePath::getImagePath).toList()
        );
    }
}
