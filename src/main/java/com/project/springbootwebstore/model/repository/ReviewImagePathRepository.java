package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.users.Review;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImagePathRepository extends PagingAndSortingRepository<Review,Long> {
}
