package org.example.films.Repositories;

import org.example.films.Entitys.SignalementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignalementRepository extends JpaRepository<SignalementEntity, Integer> {
    List<SignalementEntity> findByStatus(Boolean status);
}
