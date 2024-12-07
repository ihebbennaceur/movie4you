package org.example.films.Services;

import org.example.films.Entitys.MovieEntity;
import org.example.films.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Méthode pour ajouter un nouveau film
    public MovieEntity addMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    // Méthode pour obtenir tous les films
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    // Méthode pour obtenir un film par son ID
    public Optional<MovieEntity> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    // Méthode pour obtenir les films par leur nom (utilisé pour la recherche)
    public List<MovieEntity> getMoviesByName(String name) {
        return movieRepository.findByNameContainingIgnoreCase(name);
    }

    // Méthode pour supprimer un film par son ID
    public void deleteMovieById(Integer id) {
        movieRepository.deleteById(id);
    }

    // Méthode pour mettre à jour un film existant
    public MovieEntity updateMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    public boolean movieExists(int id) {
        return movieRepository.existsById(id);
    }
}
