package org.example.films.Entitys;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "release_date", nullable = false)
    private String releaseDate;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "rate_imdb", nullable = false)
    private String rateImdb;

    @Column(name = "rate_user", nullable = false)
    private String rateUser;

    @Column(name = "posters", nullable = false)
    private String posters;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "plots", nullable = false)
    private String plots;

    @Column(name = "actors", nullable = false)
    private String actors;

    @Column(name = "bande_annonce", nullable = false)
    private String bandeAnnonce;

    @Column(name = "realisateur")
    private String realisateur;

//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<WatchList> watchLists = new ArrayList<>();
}
