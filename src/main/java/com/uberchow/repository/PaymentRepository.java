package com.uberchow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberchow.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
}
