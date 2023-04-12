package com.example.demo.repository;

import com.example.demo.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {

    public Optional<Horaire> findByHoraire(String horaire);
}
