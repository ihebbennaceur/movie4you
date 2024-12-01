package org.example.films.Entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seances")
public class SeanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;


    @Column(name = "date", nullable = false)
    private String date;
}
