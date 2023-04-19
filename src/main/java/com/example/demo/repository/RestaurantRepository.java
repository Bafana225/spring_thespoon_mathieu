package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNomContainingIgnoreCase(String nom);

    Restaurant findRestaurantById(Long id);

    void deleteRestaurantById(Long id);

}
