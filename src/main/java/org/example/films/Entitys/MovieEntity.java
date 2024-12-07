package org.example.films.Entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String slug;

    @Column
    private String releaseDate;

    @Column
    private int duration;

    @Column
    private Double rateImdb;

    @Column
    private Double rateUser;

    @Column
    private String posters;

    @Column
    private String genre;

    @Column
    private String plots;

    @Column
    private String actors;

    @Column
    private String bandeAnnonce;

    @Column
    private String realisateur;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();


    public Integer getDuration() {
        return duration;
    }}
