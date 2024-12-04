package org.example.films.Repositories;

import org.example.films.Entitys.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer> {


    List<CinemaEntity> findAll();

    List<CinemaEntity> findByNameContaining(String name);

    List<CinemaEntity> findByLocation(String location);
}

