package com.project.springbootwebstore.entity.users;

import javax.persistence.*;

@Entity
public class ReviewImagePath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    @ManyToOne
    private Review review;

    public ReviewImagePath(String imagePath, Review review) {
        this.imagePath = imagePath;
        this.review = review;
    }

  protected ReviewImagePath() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
