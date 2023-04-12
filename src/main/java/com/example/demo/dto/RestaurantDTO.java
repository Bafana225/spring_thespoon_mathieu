package com.example.demo.dto;

import com.example.demo.model.Horaire;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {

    private long id;

    private String nom;

    private int nbrPlaces;

    private boolean pmr;

    private float prixMoyen;

    private String Adresse;

    // ici au lieu d'avoir une liste de Horaires j'ai une liste de String
    private List<String> horaire;
}
