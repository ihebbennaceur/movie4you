package com.example.demo.Repositories;

import com.example.demo.Entitys.AdminEntity;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    AdminEntity findByUsername(String username);

    AdminEntity findByUsernameAndPassword(String username, String password);

    List<AdminEntity> findAll();
}
