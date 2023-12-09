package com.example.shoppingApp.products;

import com.example.shoppingApp.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    void addProduct(Product product) {
        try{
            productRepository.save(product);
        }
        catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }

    void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }
    List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

     Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException(
                                    "product with id " + id + " not found"
                            );
                            log.error("error in getting product {}, {}", id, notFoundException.toString() );
                            return notFoundException;
                        }
                );
    }
}
