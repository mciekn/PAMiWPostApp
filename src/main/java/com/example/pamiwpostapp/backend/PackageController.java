package com.example.pamiwpostapp.backend;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Value
class Package{
    String id;
    String name;
    String content;
}

@Slf4j
@RestController
@RequestMapping(value = "packages",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class PackageController {


    @GetMapping
    Collection<Package> findAll(){
        log.debug("Find all packages");
        return null;
    }


    @GetMapping("/{id}")
    ArrayList<Package> findById(Long id){
        log.debug("Find package with id: {}", id);
        return null;
    }

    @PostMapping
    Package createPackage(@RequestBody Package aPackage){
        log.debug("Create package: {}", aPackage);
        return null;
    }

    @PutMapping
    Package putPackage(@PathVariable Long id, @RequestBody Package aPackage){
        log.debug("Find package with id: {}, with package {}", id, aPackage);
        return null;
    }

    @DeleteMapping("/{id}")
    void deletePackage(@PathVariable Long id){
        log.debug("Delete package with id: {}", id);

    }
}
