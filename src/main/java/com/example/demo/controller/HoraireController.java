package com.example.demo.controller;

import com.example.demo.model.Horaire;
import com.example.demo.repository.HoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/horaires")
public class HoraireController {

    @Autowired
    private HoraireRepository horaireRepository;

    @GetMapping("")
    public List<Horaire> getAllHoraires() {
        return horaireRepository.findAll();
    }
}
