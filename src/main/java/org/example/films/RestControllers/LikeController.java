package org.example.films.RestControllers;

import org.example.films.Entitys.LikeEntity;
import org.example.films.Services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<LikeEntity> likeReview(@RequestParam int userId, @RequestParam int reviewId) {
        LikeEntity like = likeService.likeReview(userId, reviewId);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikeReview(@RequestParam int userId, @RequestParam int reviewId) {
        likeService.unlikeReview(userId, reviewId);
        return ResponseEntity.noContent().build();
    }
}
