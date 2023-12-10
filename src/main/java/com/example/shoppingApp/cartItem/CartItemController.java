package com.example.shoppingApp.cartItem;

import com.example.shoppingApp.api_models.request.CartItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/cartItems")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping(path = "add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItemRequest request) {
        CartItem response = cartItemService
                .addProductToCart(request.email, request.productId, request.quantity);
        if (response == null) {
            return new ResponseEntity<CartItem>((CartItem) null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<CartItem>(response, HttpStatus.OK);
        }

    }
}
