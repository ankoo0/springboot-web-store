package com.project.springbootwebstore.dto.review;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ReviewRequest(
        String title,
        String review,
        Double rating,
        Long productId,
        List<MultipartFile> reviewImages
) {

}
