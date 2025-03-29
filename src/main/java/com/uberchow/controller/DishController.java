package com.uberchow.controller;

import com.uberchow.model.Dish;
import com.uberchow.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Dish>> getByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishRepository.findByRestaurantId(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        dish.setCreatedOn(LocalDateTime.now());
        return ResponseEntity.ok(dishRepository.save(dish));
    }
    
    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishRepository.findAll());
    }

}

