package com.project.springbootwebstore.model.entity.users;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToMany(mappedBy = "likedReviews")
    private List<User> likedUsers;
    @ManyToMany(mappedBy = "dislikedReviews")
    private List<User> dislikedUsers;
    private Double rating;
    private String review;
    @OneToMany(mappedBy = "review")
    private List<ReviewImagePath> reviewImagesPaths;
    private LocalDateTime dateTime;
    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public List<User> getDislikedUsers() {
        return dislikedUsers;
    }

    public void setDislikedUsers(List<User> dislikedUsers) {
        this.dislikedUsers = dislikedUsers;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<ReviewImagePath> getReviewImagesPaths() {
        return reviewImagesPaths;
    }

    public void setReviewImagesPaths(List<ReviewImagePath> reviewImagesPaths) {
        this.reviewImagesPaths = reviewImagesPaths;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
