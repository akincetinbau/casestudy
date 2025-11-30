package com.example.assesment.controller;


import com.example.assesment.model.User;
import com.example.assesment.service.ProducerService;
import com.example.assesment.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final ProducerService producerService;

    public UserController(UserService service, ProducerService  producerService) {
        this.service = service;
        this.producerService = producerService;
    }

    // CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        //producerService.sendMessage(user.getName() +" " + user.getEmail()+ " successfully created");
        return service.createUser(user);
    }

    // READ ALL
    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    // READ ONE
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return service.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        service.deleteUser(id);
    }
}
