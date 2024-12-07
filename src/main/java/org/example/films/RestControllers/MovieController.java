package org.example.films.RestControllers;

import jakarta.validation.Valid;
import org.example.films.DTO.MovieDTO;
import org.example.films.Entitys.MovieEntity;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create_movie")
    public ResponseEntity<MovieEntity> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        MovieEntity movie = new MovieEntity();
        movie.setName(movieDTO.getName());
        movie.setSlug(movieDTO.getSlug());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setRateImdb(movieDTO.getRateImdb());
        movie.setRateUser(movieDTO.getRateUser());
        movie.setPosters(movieDTO.getPosters());
        movie.setGenre(movieDTO.getGenre());
        movie.setPlots(movieDTO.getPlots());
        movie.setActors(movieDTO.getActors());
        movie.setBandeAnnonce(movieDTO.getBandeAnnonce());
        movie.setRealisateur(movieDTO.getRealisateur());

        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @GetMapping("/movie_contain/{name}")
    public ResponseEntity<List<MovieEntity>> getMovie(@PathVariable("name") String name) {
        List<MovieEntity> movies = movieService.getMoviesByName(name);
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/all")
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        List<MovieEntity> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    @Autowired
    MovieRepository movieRepository;

    @PutMapping("/edit_movie/{id}")
    public ResponseEntity<String> editMovie(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String slug,
            @RequestParam(required = false) String releaseDate,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double rateImdb,
            @RequestParam(required = false) Double rateUser,
            @RequestParam(required = false) String posters,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String plots,
            @RequestParam(required = false) String actors,
            @RequestParam(required = false) String bandeAnnonce,
            @RequestParam(required = false) String realisateur
    ) {
        Optional<MovieEntity> movieDb = movieRepository.findById(id);
        if (!movieDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        MovieEntity movie = movieDb.get();
        // Mise à jour des champs si des valeurs ont été passées
        if (name != null) movie.setName(name);
        if (slug != null) movie.setSlug(slug);
        if (releaseDate != null) movie.setReleaseDate(releaseDate);
        if (duration != null) movie.setDuration(duration);
        if (rateImdb != null) movie.setRateImdb(rateImdb);
        if (rateUser != null) movie.setRateUser(rateUser);
        if (posters != null) movie.setPosters(posters);
        if (genre != null) movie.setGenre(genre);
        if (plots != null) movie.setPlots(plots);
        if (actors != null) movie.setActors(actors);
        if (bandeAnnonce != null) movie.setBandeAnnonce(bandeAnnonce);
        if (realisateur != null) movie.setRealisateur(realisateur);

        movieRepository.save(movie);
        return ResponseEntity.ok("Movie updated successfully: " + movie.getName());
    }

}
