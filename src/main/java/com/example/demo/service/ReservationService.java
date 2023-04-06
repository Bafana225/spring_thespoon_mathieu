package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation resa) { return reservationRepository.save(resa); }

}
