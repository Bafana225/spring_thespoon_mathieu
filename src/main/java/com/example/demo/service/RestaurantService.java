package com.example.demo.service;

import com.example.demo.model.Horaire;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.HoraireRepository;
import com.example.demo.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {

    private static RestaurantRepository restaurantRepository;

    private RestaurantService restaurantService;

    private final HoraireRepository horaireRepository;

    public Restaurant create (Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public RestaurantService(RestaurantRepository restaurantRepository, HoraireRepository horaireRepository){
        RestaurantService.restaurantRepository = restaurantRepository;
        this.restaurantRepository = restaurantRepository;
        this.horaireRepository = horaireRepository;
    }

    //findAll
    public static List<Restaurant> findAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant update (Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    //findRestaurantById
    public Restaurant findRestaurantById(Long id){
        return restaurantRepository.findRestaurantById(id);
    }

    //addPerosnne
    public Restaurant addRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);

    }

    //updateRestaurant
    public Restaurant updateRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    //deletePeronne
    @Transactional
    public void deleteRestaurant(Long id){
        restaurantRepository.deleteRestaurantById(id);
    }


    public List<Object> addHoraire(long id_Horaire, long id_Restaurant) {
        Restaurant restaurant = findRestaurantById(id_Restaurant);
        Horaire horaire = horaireRepository.findHoraireById(id_Horaire);
        if (restaurant == null) {
            return Arrays.asList("Restaurant non trouvé", HttpStatus.NOT_FOUND);
        }
        if (horaire == null) {
            return Arrays.asList("Horaire non trouvée", HttpStatus.NOT_FOUND);
        }
        restaurant.getHoraire().add(horaire);
        horaire.getRestaurants().add(restaurant);
        restaurantRepository.save(restaurant);
        horaireRepository.save(horaire);
        return Arrays.asList("Horaire ajoutée à " + restaurant.getNom() + " " + "Heure : " + horaire.getHoraire(), HttpStatus.OK);
    }

    public boolean checkOpened(Restaurant restaurant, Horaire horaire) {
        /** On parcourt toutes les horaires du restaurant **/
        /** Pour chaque horaire, on vérifie si l'horaire est égal à l'horaire en paramètre **/
        for (int i = 0; i < restaurant.getHoraire().size(); i++) {
            /** Si c'est le cas, cela signifie que le restaurant est ouvert à cet horaire et la méthode renvoie true. **/
            if (restaurant.getHoraire().equals(horaire.getHoraire())) {
                return true;
            }
        }
        return false;
    }

}
