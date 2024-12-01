package org.example.films.RestControllers;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Services.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/all_cinemas")
    public List<CinemaEntity> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCinema(@Valid @RequestBody CinemaEntity cinema, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data provided");
        }
        cinemaService.createCinema(cinema);
        return ResponseEntity.ok("Cinema created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaEntity> getCinemaById(@PathVariable int id) {
        try {
            CinemaEntity cinema = cinemaService.getCinemaById(id);
            return ResponseEntity.ok(cinema);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCinema(@PathVariable int id) {
        try {
            cinemaService.deleteCinemaById(id);
            return ResponseEntity.ok("Cinema deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cinema not found");
        }
    }
}
