package com.example.shoppingApp.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductId(Long id);
    @Modifying
    @Query("update CartItem ci set ci.quantity = :quantity where ci.productId = :productId")
    void updateQuantityByProductId(@Param("quantity") Integer quantity, @Param("productId") Long productId);
}
