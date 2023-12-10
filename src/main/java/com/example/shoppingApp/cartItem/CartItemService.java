package com.example.shoppingApp.cartItem;

import com.example.shoppingApp.api_models.request.CartItemRequest;
import com.example.shoppingApp.cart.Cart;
import com.example.shoppingApp.cart.CartRepository;
import com.example.shoppingApp.products.Product;
import com.example.shoppingApp.products.ProductRepository;
import com.example.shoppingApp.user.AppUser;
import com.example.shoppingApp.user.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository userRepository;
    private final CartRepository cartRepository;

    @Transactional
    public CartItem addProductToCart(String email, Long productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);
        AppUser user = userRepository.findUserByEmail(email);
        CartItem cartItem;

        if (product.isPresent()) {
            if (user.getCart() != null) {
                if (cartItemRepository.existsByProductId(productId)) {
                    cartItem = cartItemRepository.findByProductId(productId);
                    cartItemRepository.updateQuantityByProductId(quantity, productId);
                } else {
                    Product prod = product.get();
                    cartItem = new CartItem(prod.getName(), prod.getId(), quantity);
                    cartItem.setCart(user.getCart());
                    cartItemRepository.save(cartItem);
                }
                return cartItemRepository.findById(cartItem.getId()).get();
            } else {
                Cart cart = new Cart();
                cart.setAppUser(user);
                cartRepository.save(cart);
                Product prod = product.get();
                cartItem = new CartItem(prod.getName(), prod.getId(), quantity);
                cartItem.setCart(user.getCart());
                cartItemRepository.save(cartItem);
            }
        }
        return null;
    }

    @Transactional
    public void removeProductToCart(Long id) {
        cartItemRepository.deleteAllById(Collections.singletonList(id));
    }
}
