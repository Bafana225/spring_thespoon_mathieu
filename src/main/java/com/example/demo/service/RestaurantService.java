package com.example.demo.service;

import com.example.demo.model.Horaire;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restau) { return restaurantRepository.save(restau); }

    public boolean checkOpened(Restaurant restaurant, Horaire horaire) {
        // cette méthode ne fait pas grand chose on pourrait se dire qu'elle n'est pas utile et qu'on pourrait
        // faire un .contains() directement dans notre code. Mais si demain on change la façon de gérer les horaires
        // on ne pourra peut-être plus utiliser le .contains() alors il faudra repasser partout dans le code pour
        // corriger. D'une façon générale dès qu'on a des fonctions métier comme même très simple, il faut
        // les définir dans une fonction

        // la fonction contains() retourne true si la liste contient l'elt, false sinon
        return restaurant.getHoraire().contains(horaire);
    }

}
