package org.example.films.RestControllers;

import org.example.films.Entitys.MovieEntity;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {


    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create_movie")
    public MovieEntity createMovie(@RequestBody MovieEntity movie) {
        return movieService.addMovie(movie);
    }

    @GetMapping("/movie_contain/{name}")
    public List<MovieEntity> getMovie(@PathVariable String name) {
        return movieService.getMoviesByName(name);
    }

    @GetMapping("/all")
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovieById(id);
    }

    @Autowired
    MovieRepository movieRepository;
    @PutMapping("/edit_movie/{id}")
    public ResponseEntity<String> editMovie(@PathVariable("id") Integer id, @RequestBody MovieEntity userinput) {
        Optional<MovieEntity> movie_db = movieRepository.findById(id);
        if (!movie_db.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        MovieEntity movie = movie_db.get();
        if (userinput.getName() != null) {
            movie.setName(userinput.getName());
        }
        if (userinput.getSlug() != null) {
            movie.setSlug(userinput.getSlug());
        }
        if (userinput.getReleaseDate() != null) {
            movie.setReleaseDate(userinput.getReleaseDate());
        }
        if (userinput.getDuration() > 0) { // Vérifier une valeur par défaut si nécessaire
            movie.setDuration(userinput.getDuration());
        }
        if (userinput.getRateImdb() != null) {
            movie.setRateImdb(userinput.getRateImdb());
        }
        if (userinput.getRateUser() != null) {
            movie.setRateUser(userinput.getRateUser());
        }
        if (userinput.getPosters() != null) {
            movie.setPosters(userinput.getPosters());
        }
        if (userinput.getGenre() != null) {
            movie.setGenre(userinput.getGenre());
        }
        if (userinput.getPlots() != null) {
            movie.setPlots(userinput.getPlots());
        }
        if (userinput.getActors() != null) {
            movie.setActors(userinput.getActors());
        }
        if (userinput.getBandeAnnonce() != null) {
            movie.setBandeAnnonce(userinput.getBandeAnnonce());
        }
        if (userinput.getRealisateur() != null) {
            movie.setRealisateur(userinput.getRealisateur());
        }



        movieRepository.save(movie);

        return ResponseEntity.ok("Movie updated");
    }


}

