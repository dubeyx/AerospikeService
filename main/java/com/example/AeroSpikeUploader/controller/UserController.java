package com.example.AeroSpikeUploader.controller;

import com.example.AeroSpikeUploader.model.User;
import com.example.AeroSpikeUploader.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userRepository.saveUser(user);
        return ResponseEntity.ok("User added successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userRepository.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        User existingUser = userRepository.getUser(id);
        List<String> list = new ArrayList<>();
        list.add("AKHIL");
        list.add("Dubey");
        list.add("NSIT");
        userRepository.saveUserList(list,id);;

        if (existingUser != null) {
            userRepository.saveUser(user);
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
