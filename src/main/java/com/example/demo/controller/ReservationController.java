package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;


    @GetMapping("/get/{id}")
    @Operation(summary = "Afficher une réservation", description = "Afficher une réservation par son id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<Object> findReservation(@PathVariable("id") Long id) {
        Optional<Reservation> resa = this.reservationRepository.findById(id);
        if (resa.isEmpty()) {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resa, HttpStatus.OK);
    }

    @GetMapping("/filtreIdRestaurant/{id}")
    @Operation(summary = "Afficher une réservation", description = "Afficher une réservation par son id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<Object> findReservationsByIdRestaurant(@PathVariable("id") Long id) {
        List<Reservation> resa = this.reservationService.getReservationsByIdRestaurant(id);
        if (resa.isEmpty()) {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resa, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Afficher les réservations", description = "Afficher les réservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Reservation>> findAllReservations() {
        List<Reservation> resa = this.reservationRepository.findAll();
        return new ResponseEntity<>(resa, HttpStatus.OK);
    }

}
