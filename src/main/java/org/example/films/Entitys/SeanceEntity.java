package org.example.films.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="seance")
public class SeanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //date
    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="langue", nullable = false)
    private String langue;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnoreProperties("seances") // Ignore la collection lors de la sérialisation
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    @JsonIgnoreProperties("seances") // Ignore la collection lors de la sérialisation
    private CinemaEntity cinema;
}