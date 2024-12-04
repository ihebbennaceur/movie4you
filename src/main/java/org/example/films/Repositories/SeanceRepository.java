package org.example.films.Repositories;

import org.example.films.Entitys.SeanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<SeanceEntity, Integer> {

    List<SeanceEntity> findByCinemaId(int cinemaId);
}
