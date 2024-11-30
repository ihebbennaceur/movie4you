package org.example.films.Repositories;


import org.example.films.Entitys.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findByNameContaining(String name);

List<MovieEntity> findAll();

    MovieEntity findBySlug(String slug);
    List<MovieEntity> findByGenre(String genre);

}
