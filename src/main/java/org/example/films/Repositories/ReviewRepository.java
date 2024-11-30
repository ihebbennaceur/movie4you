package org.example.films.Repositories;

import org.example.films.Entitys.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
}
