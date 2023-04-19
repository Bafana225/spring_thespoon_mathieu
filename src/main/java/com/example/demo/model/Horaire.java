package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Horaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Horaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horaire")
    private long id;

    @Column
    private String horaire;


    /** ManyToMany = Horaires --> Restaurants **/
//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER )
//    @JoinTable(name = "horaireRestaurant",
//            joinColumns = @JoinColumn(name = "id_horaire"),
//            inverseJoinColumns = @JoinColumn(name = "id_restaurant"))
//    /** On n'inclut pas propriété annotée dans la sérialisation JSON de la classe **/
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<Restaurant> restaurants;


    /** OneToMany Horaires -->  Reservations **/
    @OneToMany(mappedBy = "horaires", fetch = FetchType.LAZY, targetEntity = com.thespoon.TheSpoon.model.Reservation.class)
    @JsonIgnore
    private List<com.thespoon.TheSpoon.model.Reservation> reservations;



    /** Méthode tosString servant à avoir un retour sur Postman lorsque l'on utilise "addHoraires" **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Horaire [id=").append(id)
                .append(", horaire=").append(horaire)
                .append("]\n");
        return sb.toString();
    }

}

