package org.example.films.Services;

import org.example.films.Entitys.ReviewEntity;
import org.example.films.Entitys.SignalementEntity;
import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.ReviewRepository;
import org.example.films.Repositories.SignalementRepository;
import org.example.films.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SignalementService {

    private final SignalementRepository signalementRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public SignalementService(SignalementRepository signalementRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.signalementRepository = signalementRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public SignalementEntity createSignalement(int userId, int reviewId, String raison) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        SignalementEntity signalement = new SignalementEntity();
        signalement.setUser(user);
        signalement.setReview(review);
        signalement.setRaison(raison);
        signalement.setCreatedAt(LocalDateTime.now());
        signalement.setStatus(false); // Initial status as not reviewed

        return signalementRepository.save(signalement);
    }

    public List<SignalementEntity> getAllSignalements() {
        return signalementRepository.findAll();
    }

    public List<SignalementEntity> getPendingSignalements() {
        return signalementRepository.findByStatus(false);
    }

    public SignalementEntity updateSignalementStatus(int signalementId, Boolean status) {
        SignalementEntity signalement = signalementRepository.findById(signalementId).orElseThrow(() -> new RuntimeException("Signalement not found"));
        signalement.setStatus(status);
        return signalementRepository.save(signalement);
    }

    public void deleteSignalement(int signalementId) {
        signalementRepository.deleteById(signalementId);
    }
}
