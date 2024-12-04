package org.example.films.RestControllers;

import jakarta.validation.Valid;
import org.example.films.DTO.CinemaDTO;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/create_cinema")
    public ResponseEntity<CinemaEntity> createCinema(@Valid @RequestBody CinemaDTO cinemaDTO) {
        CinemaEntity cinema = new CinemaEntity();
        cinema.setName(cinemaDTO.getName());
        cinema.setLocation(cinemaDTO.getLocation());
        cinema.setCapacity(cinemaDTO.getCapacity());
        cinema.setContactInfo(cinemaDTO.getContactInfo());
        return ResponseEntity.ok(cinemaService.addCinema(cinema));
    }


    @GetMapping("/cinema_contain/{name}")
    public List<CinemaEntity> getCinema(@PathVariable String name) {
        return cinemaService.getCinemasByName(name);
    }

    @GetMapping("/cinemas_by_location/{location}")
    public List<CinemaEntity> getCinemasByLocation(@PathVariable String location) {
        return cinemaService.getCinemasByLocation(location);
    }


    @GetMapping("/all")
    public List<CinemaEntity> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinemaById(id);
    }

    @Autowired
    CinemaRepository cinemaRepository;

    @PutMapping("/edit_cinema/{id}")
    public ResponseEntity<String> editCinema(@PathVariable("id") Integer id, @RequestBody CinemaEntity userinput) {
        Optional<CinemaEntity> cinema_db = cinemaRepository.findById(id);
        if (!cinema_db.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        CinemaEntity cinema = cinema_db.get();
        if (userinput.getName() != null) {
            cinema.setName(userinput.getName());
        }
        if (userinput.getLocation() != null) {
            cinema.setLocation(userinput.getLocation());
        }
        if (userinput.getCapacity() > 0) {
            cinema.setCapacity(userinput.getCapacity());
        }
        if (userinput.getContactInfo() != null) {
            cinema.setContactInfo(userinput.getContactInfo());
        }

        cinemaRepository.save(cinema);
        return ResponseEntity.ok("Cinema updated");
    }
}
