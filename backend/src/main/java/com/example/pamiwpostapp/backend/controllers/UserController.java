package com.example.pamiwpostapp.backend.controllers;

import com.example.pamiwpostapp.backend.entities.PostUser;
import com.example.pamiwpostapp.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    Collection<PostUser> findAll(){
        log.debug("Find all users");
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostUser create(@RequestBody PostUser user){
        log.debug("Create user: {}", user);
        return userService.save(user);
    }

    @GetMapping("/{id}")
    PostUser findById(@PathVariable Long id){
        log.debug("Find user with id: {}", id);
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    PostUser update(@PathVariable Long id, @RequestBody PostUser user){
        log.debug("Update user with id: {}, with user {}", id, user);
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        log.debug("Delete user with id: {}", id);
        userService.deleteById(id);
    }
}
