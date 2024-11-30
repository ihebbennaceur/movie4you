package org.example.films.RestControllers;

import org.example.films.Entitys.ReviewEntity;
import org.example.films.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

        @GetMapping("/user/{userId}")
        public ResponseEntity<List<ReviewEntity>> getAllReviewsForUser(@PathVariable int userId) {
            List<ReviewEntity> reviews = reviewService.getAllReviewsByUser(userId);
            return ResponseEntity.ok(reviews);
        }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> removeReview(@PathVariable int reviewId) {
        reviewService.removeReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    }

