package com.project.springbootwebstore.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewDto

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

