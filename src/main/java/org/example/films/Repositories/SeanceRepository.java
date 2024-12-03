package org.example.films.Repositories;

import org.example.films.Entitys.SeanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<SeanceEntity, Integer> {
}