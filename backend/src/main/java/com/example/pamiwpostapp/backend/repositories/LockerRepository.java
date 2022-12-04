package com.example.pamiwpostapp.backend.repositories;

import com.example.pamiwpostapp.backend.entities.Locker;

import java.util.Collection;
import java.util.Optional;

public interface LockerRepository {
    Collection<Locker> findAll();

    Locker save(Locker user);

    Optional<Locker> findById(Long id);

    void deleteById(Long id);
}
