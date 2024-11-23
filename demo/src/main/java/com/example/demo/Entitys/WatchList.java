package com.example.demo.Entitys;

import com.example.demo.Entitys.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "watch_list")
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    private boolean watched;

    private LocalDateTime addedAt;
}
