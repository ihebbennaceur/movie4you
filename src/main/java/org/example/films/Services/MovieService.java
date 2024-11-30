package org.example.films.Services;

import org.example.films.Entitys.MovieEntity;
import org.example.films.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private  MovieRepository movieRepository;

    public MovieEntity addMovie(MovieEntity movie) {
        return movieRepository.save(movie); // persiste l'entité dasn db
    }

    public List<MovieEntity> getMoviesByName(String name) {

        return movieRepository.findByNameContaining(name);
    }

    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovieById(int id) {
        movieRepository.deleteById(id);
    }


}
