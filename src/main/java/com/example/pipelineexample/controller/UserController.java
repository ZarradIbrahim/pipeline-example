package com.example.pipelineexample.controller;

import com.example.pipelineexample.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final List<User> users = new ArrayList<>();
    private static long idCounter = 1;

    static {
        // Données initiales pour test
        users.add(new User(idCounter++, "Alice", "alice@example.com"));
        users.add(new User(idCounter++, "Bob", "bob@example.com"));
    }

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOpt = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

