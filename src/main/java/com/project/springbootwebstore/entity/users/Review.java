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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    private String text;
    @OneToMany(mappedBy = "review")
    private List<ReviewImagePath> reviewImagesPaths;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(unique = true)
    private Product product;

    public Review(Long id) {
        this.id=id;
    }

    public Review(String title, User user, Double rating, String text, LocalDateTime dateTime, Product product) {
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.text = text;
        this.dateTime = dateTime;
        this.product = product;
    }

}
