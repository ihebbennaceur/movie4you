package org.example.films.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "seances")
public class SeanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "duration", nullable = false)
    private int duration; // Duration in minutes

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    @JsonBackReference
    private CinemaEntity cinema;
}
