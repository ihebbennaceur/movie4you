package org.example.films.RestControllers;

import org.example.films.DTO.SeanceDTO;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seance")
public class SeanceController {

    private final SeanceService seanceService;

    @Autowired
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @PostMapping("/create_seance")
    public ResponseEntity<SeanceEntity> createSeance(@Valid @RequestBody SeanceDTO seanceDTO) {
        SeanceEntity seance = seanceService.addSeance(seanceDTO);
        return ResponseEntity.ok(seance);
    }

    @GetMapping("/by_cinema/{cinemaId}")
    public ResponseEntity<List<SeanceEntity>> getSeancesByCinemaId(@PathVariable int cinemaId) {
        List<SeanceEntity> seances = seanceService.getSeancesByCinemaId(cinemaId);
        return ResponseEntity.ok(seances);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSeance(@PathVariable int id) {
        seanceService.deleteSeanceById(id);
        return ResponseEntity.ok("Seance deleted successfully");
    }
}
