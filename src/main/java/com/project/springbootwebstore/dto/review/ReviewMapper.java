package com.project.springbootwebstore.dto.review;

import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.entity.users.ReviewImagePath;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ReviewMapper {

    ReviewMapper REVIEW_MAPPER_MAPPER = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "userAvatar", source = "user.avatarPath")
    @Mapping(target = "likes",  expression = "java((long) review.getLikedUsers().size())")
    @Mapping(target = "dislikes",  expression = "java((long) review.getDislikedUsers().size())")
    @Mapping(target = "dateTime",  source = "dateTime")
    @Mapping(target = "reviewImagePaths",  source = "reviewImagesPaths", qualifiedByName ="mapReviewImagesPaths" )
    ReviewResponse toReviewResponse(Review review);

    @Named("mapReviewImagesPaths")
    default List<String> mapReviewImagesPaths(List<ReviewImagePath> reviewImagesPaths){
        return reviewImagesPaths.stream().map(ReviewImagePath::getImagePath).toList();

    }

}
