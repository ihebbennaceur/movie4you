package org.example.films.Entitys;



    import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

    @Entity
    @Data
    @Table(name="cinema")
    public class CinemaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name="name", nullable=false)
        private String name;

        @Column(name="address", nullable = false)
        private String address;

        @Column(name="number", nullable = false)
        private int number; // Utilise int au lieu de Number

        @Column(name="website", nullable = true)
        private String website;

        @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<SeanceEntity> seances = new ArrayList<>();
    }

