package org.example.films.Repositories;

import org.example.films.Entitys.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findByNameContaining(String name);
    Optional<MovieEntity> findBySlug(String slug);
    List<MovieEntity> findByGenre(String genre);

    List<MovieEntity> findByNameContainingIgnoreCase(String name);
    List<MovieEntity> findByName(String name);
}

