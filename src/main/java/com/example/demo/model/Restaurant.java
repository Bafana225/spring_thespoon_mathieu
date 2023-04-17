package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32)
    @NotBlank
    private String nom;

    @Column(length = 32)
    private String Adresse;

    @Min(value = 0, message = "T'es bourré ? La valeur peut pas être négative")
    @Max(value = 999, message = "Ça va les chevilles ?")
    private int nbrPlaces;

    private boolean pmr;

    @Min(value = 8, message = "Socialiste")
    @Max(value = 250, message = "Escroc")
    private float prixMoyen;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Horaire> horaire;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

}
