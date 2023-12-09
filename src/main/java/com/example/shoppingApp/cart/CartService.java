package com.example.shoppingApp.cart;

import com.example.shoppingApp.exception.NotFoundException;
import com.example.shoppingApp.user.AppUser;
import com.example.shoppingApp.user.AppUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final EntityManager entityManager;

    private final CartRepository cartRepository;
    private final AppUserRepository appUserRepository;

    @Transactional
    public Cart addCart(String email) {
        AppUser user = appUserRepository.findUserByEmail(email);
        if (user == null) throw new NotFoundException("User with email " + email + " not found");
        Cart cart = new Cart();
        cart.setAppUser(user);
        return cartRepository.save(cart);
    }

    @Transactional
    public void removeCart(String email) throws NotFoundException {
        cartRepository.deleteByAppUserEmail(email);
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> {
                    return new NotFoundException("Cart with id " + id + " not found");
                });
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

}
