package com.example.demo.Services;

import com.example.demo.Entitys.UserEntity;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();

    }
    public UserEntity getUserByUserName(String UserName) {
        return userRepository.findByUsername(UserName);
    }

    public UserEntity getUserById(int id) {return userRepository.findById(id).get();}

    public void deleteUserById(int id) {userRepository.deleteById(id);}
}
