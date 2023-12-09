package com.example.shoppingApp.cart;

import com.example.shoppingApp.api_models.response.CartResponse;
import com.example.shoppingApp.api_models.response.UserResponse;
import com.example.shoppingApp.user.AppUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/carts")
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartController (CartService cartService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.modelMapper = modelMapper;

        TypeMap<Cart, CartResponse> typeMap = this.modelMapper.createTypeMap(Cart.class, CartResponse.class);
        typeMap.addMappings(mapper -> {
            mapper.map(src -> src.getAppUser().getEmail(), CartResponse::setUserEmail);
        });
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        List<CartResponse> response = new ArrayList<CartResponse>();

        carts.forEach(cart -> {
            response.add(modelMapper.map(cart, CartResponse.class));
        });
        return new ResponseEntity<List<CartResponse>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{cartId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("cartId") Long id) {
        return new ResponseEntity<CartResponse>(
                modelMapper.map(cartService.getCart(id),
                        CartResponse.class
                ),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "add")
    public ResponseEntity<CartResponse> addCart(@RequestParam String email) {
        CartResponse cartResponse = modelMapper
                .map(cartService.addCart(email), CartResponse.class);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete")
    public String removeCart(@RequestParam String email) {
        cartService.removeCart(email);
        return "Cart deleted successfully";
    }
}
