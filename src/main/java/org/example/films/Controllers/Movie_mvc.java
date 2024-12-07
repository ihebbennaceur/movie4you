package org.example.films.Controllers;

import org.example.films.Entitys.MovieEntity;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie")
public class Movie_mvc {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/all_movies")
    public String getAllMovies(Model model) {
        List<MovieEntity> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movie/list";
    }



    @GetMapping("/movie/{id}")
    public String showMovieDetails(@PathVariable("id") Integer id, Model model) {
        Optional<MovieEntity> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            model.addAttribute("movie", movieOptional.get());
            return "movie/details";
        }
        model.addAttribute("error", "Movie not found!");
        return "error/404";
    }

    @GetMapping("/edit_movie/{id}")
    public String showEditMovieForm(@PathVariable("id") Integer id, Model model) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            return "error/404";
        }
        model.addAttribute("movie", movie.get());
        return "movie/edit";
    }

    @PostMapping("/edit_movie/{id}")
    public String editMovie(@PathVariable("id") Integer id, @ModelAttribute MovieEntity movieInput, Model model) {
        Optional<MovieEntity> optionalMovie = movieRepository.findById(id);
        if (!optionalMovie.isPresent()) {
            model.addAttribute("error", "Movie not found!");
            return "error/404";
        }

        MovieEntity existingMovie = optionalMovie.get();
        updateMovieFields(existingMovie, movieInput);
        movieRepository.save(existingMovie);

        return "redirect:/movie/all_movies";
    }

    @GetMapping("/delete_movie/{id}")
    public String deleteMovie(@PathVariable("id") Integer id, Model model) {
        if (!movieRepository.existsById(id)) {
            model.addAttribute("error", "Movie not found!");
            return "error/404";
        }

        movieService.deleteMovieById(id);
        return "redirect:/movie/all_movies";
    }

    private void updateMovieFields(MovieEntity existingMovie, MovieEntity movieInput) {
        if (movieInput.getName() != null && !movieInput.getName().trim().isEmpty())
            existingMovie.setName(movieInput.getName());
        if (movieInput.getReleaseDate() != null)
            existingMovie.setReleaseDate(movieInput.getReleaseDate());
        if (movieInput.getDuration() != null)
            existingMovie.setDuration(movieInput.getDuration());
        if (movieInput.getRateImdb() != null)
            existingMovie.setRateImdb(movieInput.getRateImdb());
        if (movieInput.getRateUser() != null)
            existingMovie.setRateUser(movieInput.getRateUser());
        if (movieInput.getPosters() != null)
            existingMovie.setPosters(movieInput.getPosters());
        if (movieInput.getGenre() != null)
            existingMovie.setGenre(movieInput.getGenre());
        if (movieInput.getPlots() != null)
            existingMovie.setPlots(movieInput.getPlots());
        if (movieInput.getActors() != null)
            existingMovie.setActors(movieInput.getActors());
        if (movieInput.getBandeAnnonce() != null)
            existingMovie.setBandeAnnonce(movieInput.getBandeAnnonce());
        if (movieInput.getRealisateur() != null)
            existingMovie.setRealisateur(movieInput.getRealisateur());
    }
}
