package com.project.springbootwebstore.model.entity.users;

import javax.persistence.*;

@Entity
public class ReviewImagePath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    @ManyToOne
    private Review review;
}
