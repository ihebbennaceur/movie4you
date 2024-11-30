package org.example.films.Services;

import org.example.films.Entitys.MovieEntity;
import org.example.films.Entitys.ReviewEntity;
import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Repositories.UserRepository;
import org.example.films.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewEntity addReview(int userId, int movieId, String content) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        MovieEntity movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));

        ReviewEntity review = new ReviewEntity();
        review.setUser(user);
        review.setMovie(movie);
        review.setContent(content);
        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public List<ReviewEntity> getAllReviewsByUser(int userId) {
        return reviewRepository.findByUserId(userId);
    }

    public void removeReview(int reviewId) {
        Optional<ReviewEntity> review = reviewRepository.findById(reviewId);
        review.ifPresent(reviewRepository::delete);
    }
}
