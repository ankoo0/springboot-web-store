package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.users.Review;
import com.project.springbootwebstore.entity.users.ReviewImagePath;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImagePathRepository extends PagingAndSortingRepository<ReviewImagePath,Long> {
}
