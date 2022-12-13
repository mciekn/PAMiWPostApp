package com.example.pamiwpostapp.backend.controllers;

import com.example.pamiwpostapp.backend.entities.Locker;
import com.example.pamiwpostapp.backend.services.LockerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "lockers")
@RequiredArgsConstructor
public class LockerController {

    private final LockerService lockerService;

    @GetMapping
    Collection<Locker> findAll(){
        log.debug("Find all lockers");
        return lockerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Locker create(@RequestBody Locker locker){
        log.debug("Create locker: {}", locker);
        return lockerService.save(locker);
    }

    @GetMapping("/{id}")
    Locker findById(@PathVariable Long id){
        log.debug("Find locker with id: {}", id);
        return lockerService.findById(id);
    }

    @PutMapping("/{id}")
    Locker update(@PathVariable Long id, @RequestBody Locker locker){
        log.debug("Update locker with id: {}, with locker {}", id, locker);
        return lockerService.update(id, locker);
    }

    @DeleteMapping("/{id}")
    void deleteLocker(@PathVariable Long id){
        log.debug("Delete locker with id: {}", id);
        lockerService.deleteById(id);
    }
}
