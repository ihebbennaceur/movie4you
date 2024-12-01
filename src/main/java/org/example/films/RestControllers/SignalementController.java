package org.example.films.RestControllers;

import org.example.films.Entitys.SignalementEntity;
import org.example.films.Services.SignalementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/signalements")
public class SignalementController {

    private final SignalementService signalementService;

    public SignalementController(SignalementService signalementService) {
        this.signalementService = signalementService;
    }

    @PostMapping
    public ResponseEntity<SignalementEntity> createSignalement(@RequestParam int userId, @RequestParam int reviewId, @RequestParam String raison) {
        SignalementEntity signalement = signalementService.createSignalement(userId, reviewId, raison);
        return ResponseEntity.ok(signalement);
    }

    @GetMapping
    public ResponseEntity<List<SignalementEntity>> getAllSignalements() {
        List<SignalementEntity> signalements = signalementService.getAllSignalements();
        return ResponseEntity.ok(signalements);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<SignalementEntity>> getPendingSignalements() {
        List<SignalementEntity> signalements = signalementService.getPendingSignalements();
        return ResponseEntity.ok(signalements);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SignalementEntity> updateSignalementStatus(@PathVariable int id, @RequestParam Boolean status) {
        SignalementEntity updatedSignalement = signalementService.updateSignalementStatus(id, status);
        return ResponseEntity.ok(updatedSignalement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignalement(@PathVariable int id) {
        signalementService.deleteSignalement(id);
        return ResponseEntity.noContent().build();
    }
}
