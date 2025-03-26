package com.uberchow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uberchow.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByOrderId(Long orderId);
}