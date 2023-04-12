package com.example.demo.controller;

import com.example.demo.model.Horaire;
import com.example.demo.repository.HoraireRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/horaires")
public class HoraireController {

    @Autowired
    private HoraireRepository horaireRepository;

    @GetMapping("/all")
    @Operation(summary = "Afficher les horaires", description = "Afficher tous les horaires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Horaire>> findAllHoraires() {
        List<Horaire> horaires = this.horaireRepository.findAll();
        return new ResponseEntity<>(horaires, HttpStatus.OK);
    }

}
