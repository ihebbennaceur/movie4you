package org.example.films.Repositories;

import org.example.films.Entitys.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer> {
}
