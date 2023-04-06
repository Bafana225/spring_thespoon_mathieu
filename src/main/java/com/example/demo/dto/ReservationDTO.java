package com.example.demo.dto;

import com.example.demo.model.Horaire;
import com.example.demo.model.Restaurant;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private long id;

    private int nbrAdultes;

    private int nbrEnfants;

    // ici au lieu d'avoir une type Horaire j'ai un type String
    private String heureReservation;

    // ici au lieu d'avoir un type Restaurant j'ai l'id du restaurant
    private Long restaurant;
}
