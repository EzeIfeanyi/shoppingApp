package com.example.shoppingApp.cartItem;

import com.example.shoppingApp.cart.Cart;
import com.example.shoppingApp.user.AppUser;
import com.example.shoppingApp.user.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final AppUserRepository userRepository;
    @Transactional
    public void addProductToCart(String email, String productName, Long productId, Integer quantity) {
        AppUser user = userRepository.findUserByEmail(email);
        Cart cart = user.getCart();
        CartItem cartItem = new CartItem(productName, productId, quantity);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeProductToCart(Long id) {
        cartItemRepository.deleteAllById(Collections.singletonList(id));
    }
}
