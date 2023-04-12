package com.example.demo;

import com.example.demo.model.Horaire;
import com.example.demo.model.Reservation;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.HoraireRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RestaurantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReservationServiceTests {

	private Horaire h1;
	private Horaire h2;
	private Horaire h3;
	private Restaurant restau1;
	private Restaurant restau2;
	private Restaurant restau3;
	private Reservation resa1;
	private Reservation resa2;
	private Reservation resa3;

	@Autowired
	private HoraireRepository horaireRepository;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@BeforeEach
	void contextLoads() {
		reservationRepository.deleteAll();
		restaurantRepository.deleteAll();

		this.h1 = this.horaireRepository.findByHoraire("11h - 12h").get();
		this.h2 = this.horaireRepository.findByHoraire("03h - 04h").get();
		this.h3 = this.horaireRepository.findByHoraire("14h - 15h").get();

		this.restau1 = new Restaurant();
		this.restau1.setNom("Le restau 1");
		this.restau1.setPrixMoyen(50);
		this.restau1.setHoraire(new HashSet<Horaire>(Set.of(this.h1, this.h2)));
		restaurantRepository.save(this.restau1);

		this.restau2 = new Restaurant();
		this.restau2.setNom("Le restau 2");
		this.restau2.setPrixMoyen(60);
		this.restau2.setHoraire(new HashSet<Horaire>(Set.of(this.h3)));
		restaurantRepository.save(this.restau2);

		this.restau3 = new Restaurant();
		this.restau3.setNom("Le restau 3");
		this.restau3.setPrixMoyen(70);
		this.restau3.setHoraire(new HashSet<Horaire>(Set.of(this.h3)));
		restaurantRepository.save(this.restau3);

		this.resa1 = new Reservation();
		this.resa1.setHeureReservation(this.h1);
		this.resa1.setRestaurant(this.restau1);
		reservationRepository.save(this.resa1);

		this.resa2 = new Reservation();
		this.resa2.setHeureReservation(this.h2);
		this.resa2.setRestaurant(this.restau1);
		reservationRepository.save(this.resa2);

		this.resa3 = new Reservation();
		this.resa3.setHeureReservation(this.h3);
		this.resa3.setRestaurant(this.restau3);
		reservationRepository.save(this.resa3);
	}


	@Test
	void getReservationsByIdRestaurantTest() {
		// on vérifie le cas où la fonction doit renvoyer une liste vide
		List<Reservation> result1 = reservationService.getReservationsByIdRestaurant(this.restau2.getId());
		assertEquals(Collections.EMPTY_LIST, result1);


		// on vérifie le cas où la fonction doit renvoyer 2 réservations sur une liste de 3 résa totales
		List<Reservation> result2 = reservationService.getReservationsByIdRestaurant(this.restau1.getId());
		assertEquals(2, result2.size());
	}


}
