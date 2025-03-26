package com.uberchow.controller;

import com.uberchow.model.OrderDetails;
import com.uberchow.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> getDetailsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailsRepository.findByOrderId(orderId));
    }
}

