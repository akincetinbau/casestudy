package com.example.assesment.service;

import com.example.assesment.model.User;
import com.example.assesment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(String id) {
        return repo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public User updateUser(String id, User newUser) {
        return repo.findById(id)
                .map(old -> {
                    old.setName(newUser.getName());
                    old.setEmail(newUser.getEmail());
                    return repo.save(old);
                })
                .orElse(null);
    }

    public void deleteUser(String id) {
        repo.deleteById(id);
    }
}
