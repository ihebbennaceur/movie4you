package org.example.films.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "review_date", nullable = false)
    private LocalDateTime reviewDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;
}
