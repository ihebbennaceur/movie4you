package org.example.films.RestControllers;

import org.example.films.Entitys.ReviewEntity;
import org.example.films.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



    @RestController
    @RequestMapping("/api/v1/reviews")
    public class ReviewController {

        @Autowired
        private ReviewService reviewService;

        @PostMapping("/add")
        public ResponseEntity<ReviewEntity> addReview(
                @RequestParam int userId,
                @RequestParam int movieId,
                @RequestParam String content) {
            ReviewEntity review = reviewService.addReview(userId, movieId, content);
            return ResponseEntity.ok(review);
        }
    }

