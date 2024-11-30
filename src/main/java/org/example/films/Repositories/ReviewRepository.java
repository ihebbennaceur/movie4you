package org.example.films.Repositories;

import org.example.films.Entitys.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
    List<ReviewEntity> findByUserId(int userId);
}
