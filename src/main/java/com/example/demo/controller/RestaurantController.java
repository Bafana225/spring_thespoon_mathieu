package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
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
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Affiche un restaurant", description = "Afficher un restaurant par son id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<Object> findRestaurant(@PathVariable("id") Long id) {
        Optional<Restaurant> restau = this.restaurantRepository.findById(id);
        if (restau.isEmpty()) {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restau, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Afficher les restaurants", description = "Afficher les restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Restaurant>> findAllRestaurants() {
        List<Restaurant> restau = this.restaurantRepository.findAll();
        return new ResponseEntity<>(restau, HttpStatus.OK);
    }

    // ----- CRUD SANS LES DTO ----- //

    @PostMapping("/add")
    @Operation(summary = "Ajouter un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "Ne pas renseigner d'ID")
    })
    public ResponseEntity<Object> addRestaurant(@RequestBody Restaurant p_restau) {
        // on va empêcher l'utilisateur de mettre un id dans sa requête, sinon il fait une instruction update
        if (p_restau.getId() != 0)
        {
            return new ResponseEntity<>("Merci de ne pas renseigner l'id.", HttpStatus.NOT_ACCEPTABLE);
        }
        Restaurant restau = this.restaurantService.addRestaurant(p_restau);
        return new ResponseEntity<>(restau, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "L'update demandé ne correspond à aucun ID en base")
    })
    public ResponseEntity<Object> updateRestaurant(@RequestBody Restaurant p_restau) {
        Optional<Restaurant> restauBdd = restaurantRepository.findById(p_restau.getId());
        if (restauBdd.isEmpty())
        {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_ACCEPTABLE);
        }
        Restaurant restau = restaurantRepository.save(p_restau);
        return new ResponseEntity<>(restau, HttpStatus.CREATED);
    }

    @PutMapping("/delete")
    @Operation(summary = "Delete un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED")
    })
    public ResponseEntity<Object> deleteRestaurant(@RequestBody Restaurant p_restau) {
        restaurantRepository.deleteById(p_restau.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ///'findRestaurantsByName'

    @GetMapping("/find-by-nom/{nom}")
    @Operation(summary = "Rechercher un restaurant par nom", description = "Rechercher un restaurant par nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<List<Restaurant>> findRestaurantsByNom(@PathVariable("nom") String nom) {
        List<Restaurant> restaurants = this.restaurantRepository.findByNomContainingIgnoreCase(nom);
        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/reservations/{id}")
    @Operation(summary = "Afficher les réservations d'un restaurant", description = "Afficher les réservations d'un restaurant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<List<Reservation>> findReservationsByRestaurantId(@PathVariable("id") long id) {
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            List<Reservation> reservations = restaurant.getReservations();
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
