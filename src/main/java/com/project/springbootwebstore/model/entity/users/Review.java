package com.project.springbootwebstore.model.entity.users;

import com.project.springbootwebstore.model.entity.users.User;

import javax.persistence.*;
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
    private Long rating;
    private String review;
    @OneToMany(mappedBy = "review")
    private List<ReviewImagePath> reviewImagesPaths;

}
