package com.example.shoppingApp.user;

import com.example.shoppingApp.cart.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Table(name = "AppUser")
@NoArgsConstructor
@Entity(name = "AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

//    @JsonIgnore
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
