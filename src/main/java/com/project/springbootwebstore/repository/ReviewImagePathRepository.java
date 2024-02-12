package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.users.ReviewImagePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImagePathRepository extends JpaRepository<ReviewImagePath,Long> {
}
