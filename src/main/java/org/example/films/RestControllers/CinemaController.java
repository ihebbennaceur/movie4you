package org.example.films.RestControllers;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Services.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinemas/")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/all_cineams")
    public List<CinemaEntity> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public CinemaEntity getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }


    @PostMapping("/create")
    public CinemaEntity saveCinema(@RequestBody CinemaEntity cinema) {
        return cinemaService.saveCinema(cinema);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinema(id);
    }
}
