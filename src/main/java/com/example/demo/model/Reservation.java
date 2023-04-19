package com.thespoon.TheSpoon.model;

import com.example.demo.model.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation", nullable = false)
    private long id;

    @Column
    @NotBlank(message = "Le nombre d'adulte(s) est requis.")
    private Integer nombreAdultes;

    @Column
    @NotBlank(message = "Le nombre d'enfant(s) est requis.")
    private Integer nombreEnfants;


    /** ManyToOne Reservation --> Restaurant **/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurant")
    private com.example.demo.model.Restaurant restaurants;

    /** ManyToOne Reservation --> Horaire **/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horaire")
    private com.example.demo.model.Horaire horaires;


    /** Notes
     * @NotNull vérifie simplement que la valeur d'un champ est non-nulle,
     * @NotBlank vérifie que la valeur est non-nulle et non-vide
     */

}
