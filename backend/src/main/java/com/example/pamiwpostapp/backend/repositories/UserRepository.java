package com.example.pamiwpostapp.backend.repositories;

import com.example.pamiwpostapp.backend.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
