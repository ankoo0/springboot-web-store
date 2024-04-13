package com.project.springbootwebstore.entity.users;

import com.project.springbootwebstore.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    public User(Long id) {
        this.id = id;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));

        return authorities;
    }
}
