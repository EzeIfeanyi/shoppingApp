package com.example.shoppingApp.products;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "{productId}")
    Product getProduct(@PathVariable("productId") Long id) {
        return productService.getProduct(id);
    }
    @GetMapping
    List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "new")
    Product addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return productService.getAllProducts().getLast();
    }

    @DeleteMapping(path = "remove/{productId}")
    String removeProduct(@PathVariable("productId") Long id) {
        Optional<Product> product = Optional.ofNullable(productService.getProduct(id));
        if (product.isPresent()) {
            productService.deleteProduct(id);
            return "Product with id: " + id + " deleted";
        } else {
            return "Product with id: " + id + " does not exist";
        }
    }
}
