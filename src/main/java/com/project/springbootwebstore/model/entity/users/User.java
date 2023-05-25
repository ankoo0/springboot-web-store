package com.project.springbootwebstore.model.entity.users;


//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatarPath;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isEnabled;
//    @Enumerated(value = EnumType.STRING)
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Review> userReviews;
    @OneToMany(mappedBy = "orderedUser")
    private List<Order> userOrders;
    @ManyToMany
    @JoinTable(
            name = "user_like_review",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    private List<Review> likedReviews;
    @ManyToMany
    @JoinTable(
            name = "user_dislike_review",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    private List<Review> dislikedReviews;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }



    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }

    public List<Review> getLikedReviews() {
        return likedReviews;
    }

    public void setLikedReviews(List<Review> likedReviews) {
        this.likedReviews = likedReviews;
    }

    public List<Review> getDislikedReviews() {
        return dislikedReviews;
    }

    public void setDislikedReviews(List<Review> dislikedReviews) {
        this.dislikedReviews = dislikedReviews;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));

        return authorities;
    }
}
