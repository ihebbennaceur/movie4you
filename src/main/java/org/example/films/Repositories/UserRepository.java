package com.example.demo.Repositories;

import com.example.demo.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
    List<UserEntity> findByUsernameContaining(String username);
    UserEntity findByEmail(String email);
}
