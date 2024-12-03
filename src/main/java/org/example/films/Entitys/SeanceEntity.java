package org.example.films.Entitys;

import jakarta.persistence.*;
import lombok.Data;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Entitys.MovieEntity;

import java.util.Date;

@Entity
@Data
@Table(name = "seance")
public class SeanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "langue", nullable = false)
    private String langue;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;
}
