package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant", nullable = false)
    private long id;

    @Column
    @NotBlank(message = "Le nom du restaurant est requis.")
    @Size(max = 100)
    private String nom;

    @Column
    @NotBlank(message = "L'adresse du restaurant est requise.")
    @Size(max = 300)
    private String adresse;

    @Column
    @NotBlank(message = "Le nombre maximum de place du restaurant est requis.")
    @Size(min = 1)
    private Integer nombrePlaceMax;

    @Column
    @NotBlank(message = "Le prix moyen du repas est requis.")
    @Size(min = 1)
    private Float prixMoyenRepas;

    @Column
    private Boolean pmr;


    /** ManyToMany = Restaurants --> Horaires **/
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Horaire.class)
    private List<Horaire> horaires;


    /** OneToMany = Restaurants --> Réservations **/
    @OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY)
    /** On n'inclut pas le champ annoté dans la sortie JSON **/
    @JsonIgnore
    private List<com.thespoon.TheSpoon.model.Reservation> reservations;



    /** Notes
     * @NotNull vérifie simplement que la valeur d'un champ est non-nulle,
     * @NotBlank vérifie que la valeur est non-nulle et non-vide
     */

}
