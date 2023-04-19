package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantService restaurantService;

    public Reservation addReservation(Reservation resa) { return reservationRepository.save(resa); }

    public List<Object> getReservationByNomRestauration(String nomRestauration) {
        /** On normalise le nom du restaurant en supprimant les espaces et en le mettant en minuscules **/
        String nomRestaurationNormalise = nomRestauration.replaceAll("\\s+", "").toLowerCase();
        /** On récupère tous les restaurants **/
        List<Restaurant> restaurations = restaurantService.findAllRestaurants();
        List<Reservation> reservations = new ArrayList<>();

        /** On boucle sur la liste de tous les restaurants et on compare le nom de chaque restaurant normalisé à celui fourni en entrée **/
        for (int i = 0; i < restaurations.size(); i++) {
            String nomRestaurationActuel = restaurations.get(i).getNom().replaceAll("\\s+", "").toLowerCase();
            if (nomRestaurationActuel.equals(nomRestaurationNormalise)) {
                reservations.addAll(restaurations.get(i).getReservations());
            }
        }

        if (reservations.isEmpty()) {
            return Arrays.asList("Aucune réservation pour le restaurant : " + nomRestauration, HttpStatus.NOT_FOUND);
        }

        return Arrays.asList(reservations, HttpStatus.OK);
    }


    public List<Reservation> getReservationsByIdRestaurant(Long idRestaurant) {
        /** On récupère la liste de tous les restaurants **/
        List<Restaurant> restaurants = restaurantService.findAllRestaurants();
        List<Reservation> reservations;
        Restaurant restaurant = new Restaurant();

        for (int i = 0; i < restaurants.size(); i++) {
            /**  Long.valueOf() pour convertir l'ID de type Long du restaurant en un objet Long afin de pouvoir utiliser la méthode equals() **/
            if (Long.valueOf(restaurants.get(i).getId()).equals(idRestaurant)) {
                restaurant = restaurants.get(i);
            }
        }
        reservations = restaurant.getReservations();
        return reservations;
    }


}
