package com.project.springbootwebstore.dto.review;

import java.util.List;

public record ReviewResponse

    (Long id,
    String username,
    String userAvatar,
    Long likes,
    Long dislikes,
    Double rating,
    String title,
    String text,
    String dateTime,
    List<String> reviewImagePaths){}

