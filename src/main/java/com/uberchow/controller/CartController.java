package com.uberchow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberchow.model.Cart;
import com.uberchow.model.CartDetails;
import com.uberchow.repository.CartDetailsRepository;
import com.uberchow.repository.CartRepository;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartRepository.findByUserId(userId));
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartDetails>> getCartItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartDetailsRepository.findByCartId(cartId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDetails> addItemToCart(@RequestBody CartDetails cartDetails) {
        return ResponseEntity.ok(cartDetailsRepository.save(cartDetails));
    }
}

