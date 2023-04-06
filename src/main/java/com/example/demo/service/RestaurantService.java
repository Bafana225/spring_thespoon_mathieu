package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restau) { return restaurantRepository.save(restau); }

}
