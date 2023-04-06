package com.example.demo.dto;

import com.example.demo.model.Horaire;
import com.example.demo.model.Reservation;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.HoraireRepository;
import com.example.demo.repository.RestaurantRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@Service
public class Mapper {

    @Autowired
    HoraireRepository horaireRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant restaurantDTOtoRestaurant(RestaurantDTO p_restau) {
        Restaurant result = new Restaurant();
        result.setId(p_restau.getId());
        result.setNom(p_restau.getNom());
        result.setNbrPlaces(p_restau.getNbrPlaces());
        result.setPmr(p_restau.isPmr());
        result.setPrixMoyen(p_restau.getPrixMoyen());
        result.setAdresse(p_restau.getAdresse());
        result.setHoraires(new HashSet<Horaire>());
        for (String horaire : p_restau.getHoraires()) {
            Optional<Horaire> h = horaireRepository.findByValeur(horaire);
            if (h.isPresent()) {
                result.getHoraires().add(h.get());
            }
        }
        return result;
    }

    public Reservation reservationDTOtoReservation(ReservationDTO p_resa) {
        Reservation result = new Reservation();
        result.setId(p_resa.getId());
        result.setNbrAdultes(p_resa.getNbrAdultes());
        result.setNbrEnfants(p_resa.getNbrEnfants());
        Optional<Horaire> h = horaireRepository.findByValeur(p_resa.getHeureReservation());
        if (h.isPresent()) { result.setHeureReservation(h.get()); }
        Optional<Restaurant> restau = restaurantRepository.findById(p_resa.getRestaurant());
        if (restau.isPresent()) { result.setRestaurant(restau.get()); }
        return result;
    }
}
