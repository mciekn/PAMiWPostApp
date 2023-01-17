package com.example.pamiwpostapp.backend.controllers;

import com.example.pamiwpostapp.backend.entities.Package;
import com.example.pamiwpostapp.backend.services.PackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "packages")
@RequiredArgsConstructor

public class PackageController {

    private final PackageService packageService;

    @GetMapping
    Collection<com.example.pamiwpostapp.backend.entities.Package> findAll(@AuthenticationPrincipal Jwt principal){
        log.debug("Find all packages");
        return packageService.findAll()
                .stream()
                .filter(p -> p.getSender_id().equals(principal.getSubject()) || p.getReceiver_id().equals(principal.getClaim("preferred_username")) || principal.getClaim("preferred_username").equals("admin"))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    Package findById(@PathVariable Long id, @AuthenticationPrincipal Jwt principal){
        log.debug("Find package with id: {}", id);
        if(packageService.findById(id).getSender_id().equals(principal.getSubject())
                || packageService.findById(id).getReceiver_id().equals(principal.getClaim("preferred_username"))
                || principal.getClaim("preferred_username").equals("admin")){
            return packageService.findById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "401");
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    com.example.pamiwpostapp.backend.entities.Package createPackage(@RequestBody com.example.pamiwpostapp.backend.entities.Package aPackage, @AuthenticationPrincipal Jwt principal){
        log.debug("Create package: {}", aPackage);
        aPackage.setSender_id(principal.getSubject());
        return packageService.save(aPackage);
    }

    @PutMapping("/{id}")
    com.example.pamiwpostapp.backend.entities.Package update(@PathVariable Long id, @RequestBody com.example.pamiwpostapp.backend.entities.Package aPackage, @AuthenticationPrincipal Jwt principal){
        log.debug("Find package with id: {}, with package {}", id, aPackage);
        if(packageService.findById(id).getSender_id().equals(principal.getSubject())
                || packageService.findById(id).getReceiver_id().equals(principal.getClaim("preferred_username"))
                || principal.getClaim("preferred_username").equals("admin")){
            return packageService.update(id, aPackage);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "401");
        }
    }

    @DeleteMapping("/{id}")
    void deletePackage(@PathVariable Long id, @AuthenticationPrincipal Jwt principal){
        log.debug("Delete package with id: {}", id);
        if(packageService.findById(id).getSender_id().equals(principal.getSubject())
                || packageService.findById(id).getReceiver_id().equals(principal.getClaim("preferred_username"))
                || principal.getClaim("preferred_username").equals("admin")){
            packageService.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "401");
        }

    }
}
