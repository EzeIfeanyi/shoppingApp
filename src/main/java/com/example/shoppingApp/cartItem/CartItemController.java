package com.example.shoppingApp.cartItem;

import com.example.shoppingApp.api_models.request.CartItemRequest;
import com.example.shoppingApp.api_models.response.CartItemResponse;
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
    public ResponseEntity<CartItemResponse<CartItem>> addCartItem(@RequestBody CartItemRequest request) {
        CartItem response = cartItemService
                .addProductToCart(request.email, request.productId, request.quantity);

        CartItemResponse<CartItem> cartItemCartItemResponse = new CartItemResponse<>();
        if (response == null) {
            cartItemCartItemResponse.setMessage("product not available in product list");
            cartItemCartItemResponse.setIsSuccess(false);
            return new ResponseEntity<>(cartItemCartItemResponse, HttpStatus.BAD_REQUEST);
        } else {
            cartItemCartItemResponse.setData(response);
            return new ResponseEntity<>(cartItemCartItemResponse, HttpStatus.OK);
        }

    }
}
