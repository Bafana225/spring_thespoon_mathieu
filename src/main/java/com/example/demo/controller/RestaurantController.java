package com.example.demo.controller;

import com.example.demo.dto.Mapper;
import com.example.demo.dto.RestaurantDTO;
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

    @Autowired
    Mapper mapper;

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

    // ----- CRUD AVEC LES DTO ----- //

    @PostMapping("/addDTO")
    @Operation(summary = "Ajouter un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "Ne pas renseigner d'ID")
    })
    public ResponseEntity<Object> addRestaurant(@RequestBody RestaurantDTO p_restauDTO) {
        // on va empêcher l'utilisateur de mettre un id dans sa requête, sinon il fait une instruction update
        if (p_restauDTO.getId() != 0)
        {
            return new ResponseEntity<>("Merci de ne pas renseigner l'id.", HttpStatus.NOT_ACCEPTABLE);
        }
        Restaurant restau = this.restaurantService.addRestaurant(
                mapper.restaurantDTOtoRestaurant(p_restauDTO));
        return new ResponseEntity<>(restau, HttpStatus.CREATED);
    }

    @PutMapping("/updateDTO")
    @Operation(summary = "Update un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "L'update demandé ne correspond à aucun ID en base")
    })
    public ResponseEntity<Object> updateRestaurant(@RequestBody RestaurantDTO p_restauDTO) {
        Optional<Restaurant> restauBdd = restaurantRepository.findById(p_restauDTO.getId());
        if (restauBdd.isEmpty())
        {
            return new ResponseEntity<>("Merci de renseigner un ID valide.", HttpStatus.NOT_ACCEPTABLE);
        }
        Restaurant restau = restaurantRepository.save(
                mapper.restaurantDTOtoRestaurant(p_restauDTO));
        return new ResponseEntity<>(restau, HttpStatus.CREATED);
    }

    @PutMapping("/deleteDTO")
    @Operation(summary = "Delete un restaurant", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED")
    })
    public ResponseEntity<Object> deleteRestaurant(@RequestBody RestaurantDTO p_restauDTO) {
        restaurantRepository.deleteById(p_restauDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
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

}
