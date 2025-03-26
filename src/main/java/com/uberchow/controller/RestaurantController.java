package com.uberchow.controller;

import com.uberchow.model.Restaurant;
import com.uberchow.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id)
            .map(restaurant -> {
                restaurant.setName(updatedRestaurant.getName());
                restaurant.setAddress(updatedRestaurant.getAddress());
                restaurant.setPhone(updatedRestaurant.getPhone());
                restaurant.setContact(updatedRestaurant.getContact());
                restaurant.setPicture(updatedRestaurant.getPicture());
                restaurant.setCity(updatedRestaurant.getCity());
                return ResponseEntity.ok(restaurantRepository.save(restaurant));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantRepository.save(restaurant));
    }
}

