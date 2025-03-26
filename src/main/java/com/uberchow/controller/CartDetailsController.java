package com.uberchow.controller;

import com.uberchow.model.CartDetails;
import com.uberchow.repository.CartDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-details")
public class CartDetailsController {

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartDetails>> getItemsByCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartDetailsRepository.findByCartId(cartId));
    }

    @PostMapping
    public ResponseEntity<CartDetails> addToCart(@RequestBody CartDetails cartDetails) {
        return ResponseEntity.ok(cartDetailsRepository.save(cartDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

