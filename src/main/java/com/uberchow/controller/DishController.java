package com.uberchow.controller;

import com.uberchow.model.Dish;
import com.uberchow.model.Restaurant;
import com.uberchow.repository.DishRepository;
import com.uberchow.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


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
    
    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish updatedDish) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Dish existingDish = optionalDish.get();

        existingDish.setName(updatedDish.getName());
        existingDish.setDescription(updatedDish.getDescription());
        existingDish.setPrice(updatedDish.getPrice());
        existingDish.setPicture(updatedDish.getPicture());

        if (updatedDish.getRestaurant() != null && updatedDish.getRestaurant().getId() != null) {
            Optional<Restaurant> restaurantOpt = restaurantRepository.findById(updatedDish.getRestaurant().getId());
            if (restaurantOpt.isPresent()) {
                existingDish.setRestaurant(restaurantOpt.get());
            } else {
                return ResponseEntity.badRequest().body(null); // Invalid restaurantId
            }
        }

        dishRepository.save(existingDish);
        return ResponseEntity.ok(existingDish);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        return dishRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        if (!dishRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        dishRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

