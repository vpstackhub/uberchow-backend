package com.uberchow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberchow.model.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    List<CartDetails> findByCartId(Long cartId);
}
