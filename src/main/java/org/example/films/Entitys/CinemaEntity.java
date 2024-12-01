package org.example.films.Entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cinemas")
public class CinemaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be empty")
    private String address;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeanceEntity> seances = new ArrayList<>();
}
