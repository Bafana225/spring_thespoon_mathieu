package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public Reservation addReservation(Reservation resa) { return reservationRepository.save(resa); }

    public List<Reservation> getReservationsByIdRestaurant(long idRestau) {
        Optional<Restaurant> restau = restaurantRepository.findById(idRestau);
        if (restau.isPresent()) {
            Optional<List<Reservation>> listeRestau = reservationRepository.findByRestaurant(restau.get());
            return listeRestau.get();
        }
        return new ArrayList<>();
    }

}
