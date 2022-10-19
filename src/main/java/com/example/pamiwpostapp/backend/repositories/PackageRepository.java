package com.example.pamiwpostapp.backend.repositories;

import com.example.pamiwpostapp.backend.entities.Package;

import java.util.Collection;
import java.util.Optional;

public interface PackageRepository {

    Collection<Package> findAll();

    Package save(Package aPackage);

    Optional<Package> findById(Long id);

    void deleteById(Long id);
}
