package org.example.films.Entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name="users")
@Data
@Entity
@Getter
@Setter

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="Profilepic",nullable = true)
    private String profilepic;

    @ElementCollection
    private List<Integer> watchList;


//    @JsonManagedReference
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<WatchList> watchLists = new ArrayList<>();

}
