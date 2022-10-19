package com.example.pamiwpostapp.backend.controllers;

import com.example.pamiwpostapp.backend.entities.Package;
import com.example.pamiwpostapp.backend.services.PackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "packages",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class PackageController {

    private final PackageService packageService;

    @GetMapping
    Collection<com.example.pamiwpostapp.backend.entities.Package> findAll(){
        log.debug("Find all packages");
        return packageService.findAll();
    }


    @GetMapping("/{id}")
    Package findById(@PathVariable Long id){
        log.debug("Find package with id: {}", id);
        return packageService.findById(id);
    }

    @PostMapping
    com.example.pamiwpostapp.backend.entities.Package createPackage(@RequestBody com.example.pamiwpostapp.backend.entities.Package aPackage){
        log.debug("Create package: {}", aPackage);
        return packageService.save(aPackage);
    }

    @PutMapping("/{id}")
    com.example.pamiwpostapp.backend.entities.Package update(@PathVariable Long id, @RequestBody com.example.pamiwpostapp.backend.entities.Package aPackage){
        log.debug("Find package with id: {}, with package {}", id, aPackage);
        return packageService.update(id, aPackage);
    }

    @DeleteMapping("/{id}")
    void deletePackage(@PathVariable Long id){
        log.debug("Delete package with id: {}", id);
        packageService.deleteById(id);
    }
}
