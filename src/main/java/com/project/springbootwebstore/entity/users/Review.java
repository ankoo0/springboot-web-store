package com.project.springbootwebstore.entity.users;

import com.project.springbootwebstore.entity.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
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
    @JoinColumn(unique = true)
    private Product product;

    public Review( String title, User user, List<User> likedUsers, List<User> dislikedUsers, Double rating, String review, List<ReviewImagePath> reviewImagesPaths, LocalDateTime dateTime, Product product) {

        this.title = title;
        this.user = user;
        this.likedUsers = likedUsers;
        this.dislikedUsers = dislikedUsers;
        this.rating = rating;
        this.review = review;
        this.reviewImagesPaths = reviewImagesPaths;
        this.dateTime = dateTime;
        this.product = product;
    }

    public Review() {
    }

    public Review(Long id) {
        this.id=id;
    }

    public Review(String title, User user, Double rating, String review, LocalDateTime dateTime, Product product) {
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.review = review;
        this.dateTime = dateTime;
        this.product = product;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
