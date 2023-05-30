package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.users.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Review> findAllByProductId(Pageable reviewPages, Long productId);
    long countReviewsByProductId(Long productId);

}
