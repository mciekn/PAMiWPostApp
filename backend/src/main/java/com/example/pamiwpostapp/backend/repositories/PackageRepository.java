package com.example.pamiwpostapp.backend.repositories;

import com.example.pamiwpostapp.backend.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<com.example.pamiwpostapp.backend.entities.Package, Long> {

}
