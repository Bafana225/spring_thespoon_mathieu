package com.example.demo.controller;

import com.example.demo.dto.Mapper;
import com.example.demo.dto.ReservationDTO;
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

    @Autowired
    Mapper mapper;

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

    // ----- CRUD AVEC LES DTO ----- //

    @PostMapping("/addDTO")
    @Operation(summary = "Ajouter une réservation", description = "Champs obligatoires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "Ne pas renseigner d'ID")
    })
    public ResponseEntity<Object> addReservation(@RequestBody ReservationDTO p_resaDTO) {
        // on va empêcher l'utilisateur de mettre un id dans sa requête, sinon il fait une instruction update
        if (p_resaDTO.getId() != 0)
        {
            return new ResponseEntity<>("Merci de ne pas renseigner l'id.", HttpStatus.NOT_ACCEPTABLE);
        }
        Reservation resa = this.reservationService.addReservation(
                mapper.reservationDTOtoReservation(p_resaDTO));
        return new ResponseEntity<>(resa, HttpStatus.CREATED);
    }

    @PutMapping("/updateDTO")
    @Operation(summary = "Update une réservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "L'update demandé ne correspond à aucun ID en base")
    })
    public ResponseEntity<Object> updateReservation(@RequestBody ReservationDTO p_resaDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(p_resaDTO.getId());

        if (reservationOptional.isEmpty()) {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_ACCEPTABLE);
        }

        Reservation reservation = mapper.reservationDTOtoReservation(p_resaDTO);
        Reservation savedReservation = reservationRepository.save(reservation);

        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }


    @PutMapping("/deleteDTO")
    @Operation(summary = "Delete un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED")
    })
    public ResponseEntity<Object> deleteRestaurant(@RequestBody ReservationDTO p_resaDTO) {
        reservationRepository.deleteById(p_resaDTO.getId());
        return new ResponseEntity<>(p_resaDTO, HttpStatus.OK);
    }

}
