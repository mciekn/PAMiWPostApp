package com.example.pamiwpostapp.backend.controllers;

import com.example.pamiwpostapp.backend.entities.User;
import com.example.pamiwpostapp.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    Collection<User> findAll(){
        log.debug("Find all users");
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User create(@RequestBody User user){
        log.debug("Create user: {}", user);
        return userService.save(user);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Long id){
        log.debug("Find user with id: {}", id);
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    User update(@PathVariable Long id, @RequestBody User user){
        log.debug("Update user with id: {}, with user {}", id, user);
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        log.debug("Delete user with id: {}", id);
        userService.deleteById(id);
    }
}
