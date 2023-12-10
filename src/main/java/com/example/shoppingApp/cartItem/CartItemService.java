package com.example.shoppingApp.cartItem;

import com.example.shoppingApp.api_models.request.CartItemRequest;
import com.example.shoppingApp.cart.Cart;
import com.example.shoppingApp.products.Product;
import com.example.shoppingApp.products.ProductRepository;
import com.example.shoppingApp.user.AppUser;
import com.example.shoppingApp.user.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
    @Transactional
    public CartItem addProductToCart(String email, Long productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);
        AppUser user = userRepository.findUserByEmail(email);

        if (product.isPresent()) {
            if (!cartItemRepository.existsById(productId)) {
                Product prod = product.get();
                CartItem cartItem = new CartItem(prod.getName(), prod.getId(), quantity);
                cartItem.setCart(user.getCart());
                return cartItemRepository.save(cartItem);
            } else {
                CartItem cartItem = cartItemRepository.findByProductId(productId);
                cartItemRepository.updateQuantityByProductId(3, productId);
                return cartItemRepository.findById(cartItem.getId()).get();
            }
        }
        return null;
    }

    @Transactional
    public void removeProductToCart(Long id) {
        cartItemRepository.deleteAllById(Collections.singletonList(id));
    }
}
