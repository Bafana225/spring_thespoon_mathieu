package com.example.demo;

import com.example.demo.model.Horaire;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.HoraireRepository;
import com.example.demo.service.RestaurantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantServiceTests {

	private Horaire h1;
	private Horaire h2;
	private Horaire h3;
	private Restaurant restau1;

	@Autowired
	private HoraireRepository horaireRepository;
	@Autowired
	private RestaurantService restaurantService;

	@BeforeEach
	void contextLoads() {
		this.h1 = this.horaireRepository.findByValeur("11h - 12h").get();
		this.h2 = this.horaireRepository.findByValeur("12h - 13h").get();
		this.h2 = this.horaireRepository.findByValeur("14h - 15h").get();
		this.restau1 = new Restaurant();
		this.restau1.setNom("Le bon restaurant");
		this.restau1.setHoraires(new HashSet<Horaire>(Set.of(this.h1, this.h2)));
	}

	@Test
	void checkOpenedTest() {
		// on vérifie le cas où la fonction doit renvoyer true
		boolean result1 = restaurantService.checkOpened(this.restau1, this.h1);
		assertEquals(true, result1);

		// on vérifie le cas où la fonction doit renvoyer false
		boolean result2 = restaurantService.checkOpened(this.restau1, this.h3);
		assertEquals(false, result2);
	}


}
