package org.example.films.RestControllers;

import org.example.films.Entitys.SeanceEntity;
import org.example.films.Services.SeanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seances")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping
    public List<SeanceEntity> getAllSeances() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    public SeanceEntity getSeanceById(@PathVariable int id) {
        return seanceService.getSeanceById(id);
    }

    @PostMapping
    public SeanceEntity saveSeance(@RequestBody SeanceEntity seance) {
        return seanceService.saveSeance(seance);
    }

    @DeleteMapping("/{id}")
    public void deleteSeance(@PathVariable int id) {
        seanceService.deleteSeance(id);
    }
}
