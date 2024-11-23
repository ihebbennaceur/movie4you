package com.example.demo.Repositories;

import com.example.demo.Entitys.MovieEntity;
import com.example.demo.Entitys.UserEntity;
import com.example.demo.Entitys.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
   Optional<WatchList> findByUserAndMovie(UserEntity user, MovieEntity movie);
}
